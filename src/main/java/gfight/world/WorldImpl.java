package gfight.world;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.WorldCamera;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventValue;
import gfight.engine.input.api.InputEventPointer;
import gfight.world.api.EntityManager;
import gfight.world.entity.api.ActiveEntity;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.hitbox.api.Hitboxes;
import gfight.world.hitbox.impl.HitboxesImpl;
import gfight.world.impl.EntityManagerImpl;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.Spawner;
import gfight.world.map.api.SpawnerFactory;
import gfight.world.map.impl.GameMapImpl;
import gfight.world.map.impl.SpawnerFactoryImpl;
import gfight.world.movement.api.InputMovement;
import gfight.world.movement.impl.MovementFactoryImpl;
import gfight.world.weapon.impl.WeaponFactoryImpl;

/**
 * Implementation of a World controlling the execution of the game.
 */
public class WorldImpl implements World {

    private static final int UP = 'W';
    private static final int DOWN = 'S';
    private static final int LEFT = 'A';
    private static final int RIGHT = 'D';

    private static final int PLAYER_DIM = 30;
    private static final int CHEST_HEALTH = 150;
    private static final int BOSS_LEVEL = 5;

    private WorldCamera camera;
    private EntityManager entityManager;
    private GameMap map;
    private InputMovement inputMapper;
    private Hitboxes hitboxManager;

    private Set<Spawner> spawners;
    private int currentLevel;
    private Character player;
    private ActiveEntity chest;
    private Position2D pointingPosition;
    private boolean isPlayerFiring;

    /**
     * Creates a new instance of a World.
     * 
     * @param mapName the name of the map to load into the world
     */
    public WorldImpl(final String mapName) {
        this.entityManager = new EntityManagerImpl(new EntityFactoryImpl());
        this.hitboxManager = new HitboxesImpl();
        this.inputMapper = new MovementFactoryImpl().createInput();
        loadMap(mapName);

    }

    @Override
    public final void installCamera(final WorldCamera camera) {
        this.camera = camera;
        this.camera.moveTo(new Position2DImpl(0, 0));
        this.camera.setArea(50, 70);
    }

    @Override
    public boolean isOver() {
        return this.player.getHealth() <= 0 || this.chest.getHealth() <= 0;
    }

    @Override
    public final void update(final long deltaTime) {
        this.player.pointTo(this.camera.getWorldPosition(this.pointingPosition));
        if (this.isPlayerFiring) {
            this.player.makeDamage();
        }
        this.hitboxManager.freeHitboxes(this.entityManager.getEntities());
        this.camera.keepInArea(this.player.getPosition());
        this.entityManager.getEntities().stream()
                .filter(e -> e instanceof MovingEntity)
                .forEach(e -> ((MovingEntity) e).updatePos(deltaTime, this.entityManager.getEntities()));
        this.entityManager.clean();
        if (this.entityManager.isClear()) {
            if (this.currentLevel % BOSS_LEVEL == 0) {
                this.spawners.stream().filter(s -> s.getType() != Spawner.SpawnerType.BOSS).forEach(Spawner::disable);
                this.spawners.stream().filter(s -> s.getType() == Spawner.SpawnerType.BOSS).forEach(Spawner::enable);
            } else {
                this.spawners.stream().filter(s -> s.getType() != Spawner.SpawnerType.BOSS).forEach(Spawner::enable);
                this.spawners.stream().filter(s -> s.getType() == Spawner.SpawnerType.BOSS).forEach(Spawner::disable);
            }
            newLevel();
        }
    }

    @Override
    public final List<GraphicsComponent> getGraphicsComponents() {
        return this.entityManager.getEntities().stream()
                .map(GameEntity::getGraphics)
                .flatMap(Set::stream)
                .toList();
    }

    @Override
    public final void processInput(final InputEvent event) {
        if (event instanceof InputEventValue) {
            manageKey((InputEventValue) event);
        } else if (event instanceof InputEventPointer) {
            managePointer((InputEventPointer) event);
        }
    }

    private void manageKey(final InputEventValue key) {
        final var direction = Optional.ofNullable(switch (key.getValue()) {
            case UP -> InputMovement.Directions.NORTH;
            case DOWN -> InputMovement.Directions.SOUTH;
            case LEFT -> InputMovement.Directions.WEST;
            case RIGHT -> InputMovement.Directions.EAST;
            default -> null;
        });
        if (direction.isPresent()) {
            if (key.getType() == InputEvent.Type.PRESSED) {
                this.inputMapper.addDirection(direction.get());
            }
            if (key.getType() == InputEvent.Type.RELEASED) {
                this.inputMapper.removeDirection(direction.get());
            }
        }
    }

    private void managePointer(final InputEventPointer pointer) {
        this.pointingPosition = pointer.getPosition();
        if (pointer.getType().equals(InputEvent.Type.MOUSE_UP)) {
            this.isPlayerFiring = false;
        } else if (pointer.getType().equals(InputEvent.Type.MOUSE_DOWN)) {
            this.isPlayerFiring = true;
        }
    }

    /**
     * Creates the game map from its name and loads
     * in memory everything from it needed to start the game.
     * 
     * @param mapName the name of the map to load
     */
    private void loadMap(final String mapName) {
        this.map = new GameMapImpl(mapName);
        this.map.getObstaclesPositions().forEach(pos -> this.entityManager.createObstacle(GameMap.TILE_DIM, pos));
        this.chest = this.entityManager.createChest(GameMap.TILE_DIM, this.map.getChestPosition(), CHEST_HEALTH);
        this.player = this.entityManager.createPlayer(PLAYER_DIM, this.map.getPlayerSpawn(), 20, inputMapper);
        new WeaponFactoryImpl().simpleGunPairing(150, 9, 4, 5, entityManager, player);
        this.pointingPosition = new Position2DImpl(this.player.getPosition().getX(), 0);
        this.currentLevel = 1;
        final SpawnerFactory spawnerFactory = new SpawnerFactoryImpl(this.entityManager, this.map);
        this.spawners = new HashSet<>();
        for (final var entry : this.map.getSpawnersPositions().entrySet()) {
            this.spawners.add(switch (entry.getValue()) {
                case BOSS -> spawnerFactory.createBoss(entry.getKey(), Set.of(this.player, this.chest));
                case SCALAR -> spawnerFactory.createScalar(entry.getKey(), Set.of(this.player, this.chest));
                case LINEAR -> spawnerFactory.createLinear(entry.getKey(), Set.of(this.player, this.chest));
            });
        }
    }

    /**
     * Tells every spawner to spawn enemies and updates current level.
     */
    private void newLevel() {
        this.spawners.stream().forEach(Spawner::spawn);
        this.currentLevel++;
    }
}

package gfight.world;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.MovableCamera;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventKey;
import gfight.engine.input.api.InputEventMouse;
import gfight.world.api.EntityManager;
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

    private static final int PLAYER_DIM = 30;
    private static final int MAP_DIM = 21;
    private static final int CHEST_HEALTH = 150;

    private MovableCamera camera;
    private EntityManager entityManager;
    private GameMap map;
    private InputMovement keyMapper;
    private Hitboxes hitboxManager;

    private Set<Spawner> spawners;
    private int currentLevel;
    private Character testPlayer;
    private Position2D pointingPosition;
    private boolean isPlayerFiring;

    /**
     * Creates a new instance of a World.
     */
    public WorldImpl() {
        this.entityManager = new EntityManagerImpl(new EntityFactoryImpl());
        this.hitboxManager = new HitboxesImpl();
        this.map = new GameMapImpl(MAP_DIM);
        this.keyMapper = new MovementFactoryImpl().createInput();
        loadMap();
        new WeaponFactoryImpl().simpleGunPairing(50, 9, 4, 5, entityManager, testPlayer);
    }

    @Override
    public final void installCamera(final MovableCamera camera) {
        this.camera = camera;
        this.camera.moveTo(new Position2DImpl(0, 0));
    }

    @Override
    public void initialize() {
    }

    @Override
    public final void update(final long deltaTime) {
        if(this.isPlayerFiring){
            this.testPlayer.makeDamage();
        }
        this.hitboxManager.freeHitboxes(this.entityManager.getEntities());
        this.testPlayer.pointTo(this.pointingPosition);
        for (final var entity : this.entityManager.getEntities()) {
            if (entity instanceof MovingEntity) {
                ((MovingEntity) entity).updatePos(deltaTime, this.entityManager.getEntities());
            }
        }
        this.entityManager.clean();
        if (this.entityManager.isClear()) {
            if (this.currentLevel % 5 == 0) {
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
        return this.entityManager.getEntities().stream().map(GameEntity::getGraphics).toList();
    }

    @Override
    public final void processInput(final InputEvent event) {
        if (event instanceof InputEventKey) {
            manageKey((InputEventKey) event);
        } else if (event instanceof InputEventMouse) {
            managePointer((InputEventMouse) event);
        }
    }

    private void manageKey(final InputEventKey key) {
        final var direction = Optional.ofNullable(switch (key.getKey()) {
            case 87 -> InputMovement.Directions.NORTH;
            case 83 -> InputMovement.Directions.SOUTH;
            case 65 -> InputMovement.Directions.WEST;
            case 68 -> InputMovement.Directions.EAST;
            default -> null;
        });
        if (direction.isPresent()) {
            if (key.getType() == InputEvent.Type.PRESSED) {
                this.keyMapper.addDirection(direction.get());
            }
            if (key.getType() == InputEvent.Type.RELEASED) {
                this.keyMapper.removeDirection(direction.get());
            }
        }
    }

    private void managePointer(final InputEventMouse pointer) {
        this.pointingPosition = pointer.getPosition();
        if (pointer.getType().equals(InputEvent.Type.MOUSE_UP)) {
            this.isPlayerFiring = false;
        } else if (pointer.getType().equals(InputEvent.Type.MOUSE_DOWN)) {
            this.isPlayerFiring = true;
        }
    }

    private void loadMap() {
        for (final var pos : this.map.getObstaclesPositions()) {
            this.entityManager.createObstacle(GameMap.TILE_DIM, pos);
        }
        this.entityManager.createChest(GameMap.TILE_DIM, this.map.getChestPosition(), CHEST_HEALTH);
        this.testPlayer = this.entityManager.createPlayer(PLAYER_DIM, this.map.getPlayerSpawn(), 20, keyMapper);
        this.pointingPosition = new Position2DImpl(this.testPlayer.getPosition().getX(), 0);
        this.currentLevel = 1;
        final SpawnerFactory spawnerFactory = new SpawnerFactoryImpl(this.entityManager, this.map);
        this.spawners = new HashSet<>();
        for (final var entry : this.map.getSpawnersPositions().entrySet()) {
            this.spawners.add(switch (entry.getValue()) {
                case BOSS -> spawnerFactory.createBoss(entry.getKey(), testPlayer);
                case SCALAR -> spawnerFactory.createScalar(entry.getKey(), testPlayer);
                case LINEAR -> spawnerFactory.createLinear(entry.getKey(), testPlayer);
            });
        }
    }

    private void newLevel() {
        this.spawners.stream().forEach(Spawner::spawn);
        this.currentLevel++;
    }
}

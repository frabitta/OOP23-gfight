package gfight.world.impl;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.WorldCamera;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventValue;
import gfight.engine.input.api.InputEventPointer;
import gfight.world.api.EntityManager;
import gfight.world.api.World;
import gfight.world.entity.api.ActiveEntity;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.hitbox.api.Hitboxes;
import gfight.world.hitbox.impl.HitboxesImpl;
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
    private static final int PLAYER_HEALTH = 150;
    private static final int PLAYER_RELOAD_TIME = 150;
    private static final int PLAYER_PROJ_SPEED = 9;
    private static final int PLAYER_PROJ_SIZE = 4;
    private static final int PLAYER_PROJ_DAMAGE = 5;
    private static final int CHEST_HEALTH = 400;
    private static final int BOSS_LEVEL = 5;

    private final EntityManager entityManager;
    private final InputMovement inputMapper;
    private final Hitboxes hitboxManager;
    private final Set<Spawner> spawners;
    private final LocalTime startTime;

    private WorldCamera camera;
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
        this.spawners = new HashSet<>();
        this.entityManager = new EntityManagerImpl(new EntityFactoryImpl());
        this.hitboxManager = new HitboxesImpl();
        this.inputMapper = new MovementFactoryImpl().createInput();
        loadMap(mapName);
        this.startTime = LocalTime.now();
    }

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "It's necessary to store and external camera to command it")
    @Override
    public final void installCamera(final WorldCamera camera) {
        this.camera = camera;
        this.camera.moveTo(new Position2DImpl(0, 0));
        final int height = 50;
        final int width = 70;
        this.camera.setArea(height, width);
    }

    @Override
    public final boolean isOver() {
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
            if (this.currentLevel % BOSS_LEVEL == 0 && this.currentLevel != 0) {
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
            manageValue((InputEventValue) event);
        } else if (event instanceof InputEventPointer) {
            managePointer((InputEventPointer) event);
        }
    }

    private void manageValue(final InputEventValue key) {
        final var direction = Optional.ofNullable(
                switch (key.getValue()) {
                    case UP -> InputMovement.Directions.NORTH;
                    case DOWN -> InputMovement.Directions.SOUTH;
                    case LEFT -> InputMovement.Directions.WEST;
                    case RIGHT -> InputMovement.Directions.EAST;
                    case RESET -> {
                        this.inputMapper.setNullDirection();
                        this.isPlayerFiring = false;
                        yield null;
                    }
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
        final GameMap map = new GameMapImpl(mapName);
        map.getObstaclesPositions().forEach(pos -> this.entityManager.createObstacle(GameMap.TILE_DIM, pos));
        this.chest = this.entityManager.createChest(GameMap.TILE_DIM, map.getChestPosition(), CHEST_HEALTH);
        this.player = this.entityManager.createPlayer(PLAYER_DIM, map.getPlayerSpawn(), PLAYER_HEALTH, inputMapper);
        this.player.setWeapon(new WeaponFactoryImpl().simpleGun(
                PLAYER_RELOAD_TIME,
                PLAYER_PROJ_SPEED,
                PLAYER_PROJ_SIZE,
                PLAYER_PROJ_DAMAGE,
                this.entityManager));
        this.pointingPosition = new Position2DImpl(this.player.getPosition().getX(), 0);
        this.currentLevel = 0;
        final SpawnerFactory spawnerFactory = new SpawnerFactoryImpl(this.entityManager, map);
        for (final var entry : map.getSpawnersPositions().entrySet()) {
            this.spawners.add(
                    switch (entry.getValue()) {
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

    @Override
    public final int getSurvivedWaves() {
        return this.currentLevel - 1;
    }

    @Override
    public final Duration getPlayedTime() {
        return Duration.between(this.startTime, LocalTime.now());
    }
}

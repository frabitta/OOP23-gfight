package gfight.world.map.impl;

import java.util.Random;
import java.util.Set;

import gfight.common.api.Position2D;
import gfight.world.entity.api.ActiveEntity;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.EntityFactory;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.Spawner;
import gfight.world.map.api.SpawnerFactory;
import gfight.world.weapon.impl.WeaponFactoryImpl;

/**
 * An implementation of a factory of different spawners.
 */
public class SpawnerFactoryImpl implements SpawnerFactory {

    private static final int ENEMY_DIM = 25;
    private static final int BOSS_DIM = 50;
    private static final int INITIAL_ENEMY_HEALTH = 5;
    private static final int INITIAL_BOSS_HEALTH = 30;

    private static final int ENEMY_RELOAD_TIME = 1750;
    private static final int ENEMY_PROJ_SPEED = 7;
    private static final int ENEMY_PROJ_SIZE = 4;
    private static final int ENEMY_PROJ_DAMAGE = 2;

    private final EntityFactory entityFactory;
    private final GameMap map;
    private final Random random;

    /**
     * Creates a new SpawnerFactory.
     * 
     * @param entityFactory the entity factory for spawning enemies
     * @param map           the game map to spawn enemies on
     */
    public SpawnerFactoryImpl(final EntityFactory entityFactory, final GameMap map) {
        this.entityFactory = entityFactory;
        this.map = map;
        this.random = new Random();
    }

    private Spawner create(
            final Position2D position,
            final Set<? extends ActiveEntity> targets,
            final int dim, final int initialHealth,
            final Spawner.SpawnerType type,
            final double statsMultiplier) {
        return new AbstractSpawner(position, type) {
            @Override
            public void spawn() {
                if (this.isEnabled()) {
                    final ActiveEntity target = type == SpawnerType.BOSS
                            ? targets.stream().filter(e -> e instanceof Character).findAny().get()
                            : targets.stream().toList().get(random.nextInt(targets.size()));
                    final double health = initialHealth
                            + (initialHealth * (getSpawnedEntities() - 1) * statsMultiplier);
                    final double damage = ENEMY_PROJ_DAMAGE
                            + (ENEMY_PROJ_DAMAGE * (getSpawnedEntities() - 1) * statsMultiplier);
                    final Character enemy = random.nextBoolean()
                            ? entityFactory.createRunner(target, dim, position, (int) health, map)
                            : entityFactory.createShooter(target, dim, position, (int) health, map);
                    enemy.setWeapon(new WeaponFactoryImpl().simpleGun(
                            ENEMY_RELOAD_TIME,
                            ENEMY_PROJ_SPEED,
                            ENEMY_PROJ_SIZE,
                            (int) damage,
                            entityFactory));

                }
                incrementSpawnedEntities();
            }
        };
    }

    @Override
    public final Spawner createLinear(final Position2D position, final Set<? extends ActiveEntity> targets) {
        return create(position, targets, ENEMY_DIM, INITIAL_ENEMY_HEALTH, Spawner.SpawnerType.LINEAR, 0);
    }

    @Override
    public final Spawner createScalar(final Position2D position, final Set<? extends ActiveEntity> targets) {
        return create(position, targets, ENEMY_DIM, INITIAL_ENEMY_HEALTH, Spawner.SpawnerType.SCALAR, 0.5);
    }

    @Override
    public final Spawner createBoss(final Position2D position, final Set<? extends ActiveEntity> targets) {
        return create(position, targets, BOSS_DIM, INITIAL_BOSS_HEALTH, Spawner.SpawnerType.BOSS, 0.5);
    }
}

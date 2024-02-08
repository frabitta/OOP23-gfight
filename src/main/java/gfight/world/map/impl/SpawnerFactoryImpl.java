package gfight.world.map.impl;

import gfight.common.api.Position2D;
import gfight.world.entity.api.ActiveEntity;
import gfight.world.entity.api.EntityFactory;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.Spawner;
import gfight.world.map.api.SpawnerFactory;

/**
 * An implementation of a factory of different spawners.
 */
public class SpawnerFactoryImpl implements SpawnerFactory {

    private static final int ENEMY_DIM = 25;
    private static final int BOSS_DIM = 50;
    private static final int INITIAL_ENEMY_HEALTH = 5;
    private static final int INITIAL_BOSS_HEALTH = 30;

    private final EntityFactory entityFactory;
    private final GameMap map;

    /**
     * Creates a new SpawnerFactory.
     * 
     * @param entityFactory the entity factory for spawning enemies
     * @param map           the game map to spawn enemies on
     */
    public SpawnerFactoryImpl(final EntityFactory entityFactory, final GameMap map) {
        this.entityFactory = entityFactory;
        this.map = map;
    }

    private Spawner create(final Position2D position, final ActiveEntity target, final int dim, final int initialHealth,
            final Spawner.SpawnerType type, final double statsMultiplier) {
        return new AbstractSpawner(position, type) {
            @Override
            public void spawn() {
                if (this.isEnabled()) {
                    final double health = initialHealth + (initialHealth * (currentLevel - 1) * statsMultiplier);
                    entityFactory.createEnemy(target, dim, position, (int) health, map);
                }
                currentLevel++;
            }
        };
    }

    @Override
    public Spawner createLinear(final Position2D position, final ActiveEntity target) {
        return create(position, target, ENEMY_DIM, INITIAL_ENEMY_HEALTH, Spawner.SpawnerType.LINEAR, 0);
    }

    @Override
    public Spawner createScalar(final Position2D position, final ActiveEntity target) {
        return create(position, target, ENEMY_DIM, INITIAL_ENEMY_HEALTH, Spawner.SpawnerType.SCALAR, 0.5);
    }

    @Override
    public Spawner createBoss(final Position2D position, final ActiveEntity target) {
        return create(position, target, BOSS_DIM, INITIAL_BOSS_HEALTH, Spawner.SpawnerType.BOSS, 0.5);
    }
}

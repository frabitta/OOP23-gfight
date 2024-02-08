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
    private static final int DEFAULT_HEALTH = 5;

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

    @Override
    public Spawner createLinear(final Position2D position, final ActiveEntity target) {
        return new AbstractSpawner(position) {
            @Override
            public void spawn() {
                entityFactory.createEnemy(target, ENEMY_DIM, position, DEFAULT_HEALTH, map);
                super.spawn();
            }
        };
    }

    @Override
    public Spawner createScalar(final Position2D position, final ActiveEntity target) {
        return new AbstractSpawner(position) {
            @Override
            public void spawn() {
                entityFactory.createEnemy(target, ENEMY_DIM, position, DEFAULT_HEALTH * this.currentLevel, map);
                super.spawn();
            }
        };
    }

    @Override
    public Spawner createBoss(final Position2D position, final ActiveEntity target) {
        // TODO
        return new AbstractSpawner(position) {
            @Override
            public void spawn() {
                super.spawn();
            }
        };
    }
}

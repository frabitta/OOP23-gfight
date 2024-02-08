package gfight.world.map.impl;

import gfight.common.api.Position2D;
import gfight.world.entity.api.EntityFactory;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.Spawner;
import gfight.world.map.api.SpawnerFactory;

/**
 * An implementation of a factory of different spawners.
 */
public class SpawnerFactoryImpl implements SpawnerFactory {

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
    public Spawner createLinear(final Position2D position) {
        return new AbstractSpawner(position) {
            @Override
            public void spawn() {
                entityFactory.createEnemy(null, currentLevel, position, this.currentLevel, map);
                super.spawn();
            }
        };
    }

    @Override
    public Spawner createScalar(final Position2D position) {
        // TODO
        return new AbstractSpawner(position) {
            @Override
            public void spawn() {
                super.spawn();
            }
        };
    }

    @Override
    public Spawner createBoss(final Position2D position) {
        // TODO
        return new AbstractSpawner(position) {
            @Override
            public void spawn() {
                super.spawn();
            }
        };
    }
}

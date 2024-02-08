package gfight.world.map.impl;

import gfight.world.entity.api.EntityFactory;
import gfight.world.map.api.Spawner;
import gfight.world.map.api.SpawnerFactory;

/**
 * An implementation of a factory of different spawners.
 */
public class SpawnerFactoryImpl implements SpawnerFactory {

    private final EntityFactory entityFactory;

    /**
     * Creates a new SpawnerFactory.
     * 
     * @param factory the entity factory for spawning enemies
     */
    public SpawnerFactoryImpl(final EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    @Override
    public Spawner createLinear() {
        // TODO
        return new AbstractSpawner(this.entityFactory) {
            @Override
            public void spawn() {
                super.spawn();
            }
        };
    }

    @Override
    public Spawner createScalar() {
        // TODO
        return new AbstractSpawner(this.entityFactory) {
            @Override
            public void spawn() {
                super.spawn();
            }
        };
    }

    @Override
    public Spawner createBoss() {
        // TODO
        return new AbstractSpawner(this.entityFactory) {
            @Override
            public void spawn() {
                super.spawn();
            }
        };
    }
}

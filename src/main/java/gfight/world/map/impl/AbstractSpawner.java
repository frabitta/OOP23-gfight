package gfight.world.map.impl;

import gfight.common.api.Position2D;
import gfight.world.map.api.Spawner;

/**
 * Implements an abstract Spawner which spawning criteria is not defined.
 */
public abstract class AbstractSpawner implements Spawner {

    private final SpawnerType type;
    private final Position2D position;
    private boolean isEnabled;
    private int spawnedEntities;

    /**
     * Creates a new abstract Spawner which spawning criteria is not defined.
     * 
     * @param position the position of the spawner
     * @param type     the type of the spawner
     */
    public AbstractSpawner(final Position2D position, final SpawnerType type) {
        this.position = position;
        this.type = type;
        this.spawnedEntities = 0;
        this.isEnabled = true;
    }

    @Override
    public final SpawnerType getType() {
        return this.type;
    }

    @Override
    public final Position2D getPosition() {
        return this.position;
    }

    @Override
    public final boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public final void enable() {
        this.isEnabled = true;
    }

    @Override
    public final void disable() {
        this.isEnabled = false;
    }

    @Override
    public final int getSpawnedEntities() {
        return this.spawnedEntities;
    }

    @Override
    public final void incrementSpawnedEntities() {
        this.spawnedEntities = this.spawnedEntities + 1;
    }
}

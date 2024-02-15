package gfight.world.map.impl;

import gfight.world.map.api.Spawner;

/**
 * Implements an abstract Spawner which spawning criteria is not defined.
 */
public abstract class AbstractSpawner implements Spawner {

    private final SpawnerType type;
    private int difficulty;

    /**
     * Creates a new abstract Spawner which spawning criteria is not defined.
     * 
     * @param position the position of the spawner
     * @param type     the type of the spawner
     */
    public AbstractSpawner(final SpawnerType type) {
        this.type = type;
        this.difficulty = 0;
    }

    @Override
    public final SpawnerType getType() {
        return this.type;
    }

    @Override
    public final int getDifficulty() {
        return this.difficulty;
    }

    @Override
    public final void incrementDifficulty() {
        this.difficulty = this.difficulty + 1;
    }
}

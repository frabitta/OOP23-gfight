package gfight.world.map.impl;

import gfight.world.entity.api.EntityFactory;
import gfight.world.map.api.Spawner;

/**
 * Implements an abstract Spawner which spawning criteria is not defined.
 */
public abstract class AbstractSpawner implements Spawner {

    protected final EntityFactory factory;

    private boolean isEnabled;
    protected int currentLevel;

    /**
     * Creates a new abstract Spawner which spawning criteria is not defined.
     * 
     * @param factory the entity factory for spawning enemies
     */
    public AbstractSpawner(final EntityFactory factory) {
        this.factory = factory;
        this.currentLevel = 0;
        this.isEnabled = true;
    }

    public void spawn(){
        this.currentLevel++;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public void enable() {
        this.isEnabled = true;
    }

    @Override
    public void disable() {
        this.isEnabled = false;
    }
}

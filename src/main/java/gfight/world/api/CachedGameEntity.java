package gfight.world.api;

/**
 * An interface for Game Entity that saves in memory hitbox and list of
 * colliding object.
 */
public interface CachedGameEntity extends GameEntity {
    /**
     * Reset the memorized hitboxes.
     */
    void reset();
}

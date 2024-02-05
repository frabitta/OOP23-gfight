package gfight.world.collision.impl;

import gfight.world.collision.api.CollisionCommand;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;

/**
 * Abstract class that implements Collision Command.
 * 
 * @param <M> is the entitiy that moves and causes the collision
 * @param <G> is the other entity
 */
public abstract class AbstractCollisionCommand<M extends MovingEntity, G extends GameEntity>
        implements CollisionCommand<M, G> {
    private final M collider;
    private final G collided;

    /**
     * 
     * @param collider
     * @param collided
     */
    public AbstractCollisionCommand(final M collider, final G collided) {
        this.collided = collided;
        this.collider = collider;
    }

    /**
     * 
     * @return the collider
     */
    protected M collider() {
        return collider;
    }

    /**
     * 
     * @return the collided
     */
    protected G collided() {
        return collided;
    }
}

package gfight.world.collision.impl;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.collision.api.CollisionCommand;

/**
 * Abstract class that implements Collision Command.
 * 
 * @param <ME> is the entitiy that moves and causes the collision
 * @param <GE> is the other entity
 */
public abstract class AbstractCollisionCommand<ME extends MovingEntity, GE extends GameEntity>
        implements CollisionCommand<ME, GE> {
    private final ME collider;
    private final GE collided;

    /**
     * 
     * @param collider
     * @param collided
     */
    public AbstractCollisionCommand(final ME collider, final GE collided) {
        this.collided = collided;
        this.collider = collider;
    }

    /**
     * 
     * @return the collider
     */
    protected ME collider() {
        return collider;
    }

    /**
     * 
     * @return the collided
     */
    protected GE collided() {
        return collided;
    }
}

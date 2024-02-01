package gfight.world.movement.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.world.api.MovingEntity;

/**
 * An implementation of BaseMovement that returns a linear or linear accelerated Movement.
 */
public final class LinearMovement extends BaseMovement {
    private final double accelleration;

    /**
     * 
     * @param direction of the vector
     */
    public LinearMovement(final Vector2D direction) {
        setDirection(direction);
        accelleration = 1;
    }

    /**
     * 
     * @param accelleration of the vector
     * @param direction of the vector
     */
    public LinearMovement(final double accelleration, final Vector2D direction) {
        setDirection(direction);
        this.accelleration = accelleration;
    }

    @Override
    public void update(final MovingEntity agent) {
        if (accelleration != 1) {
            setDirection(new Vector2D(accelleration, getDirection()));
        }
    }

}

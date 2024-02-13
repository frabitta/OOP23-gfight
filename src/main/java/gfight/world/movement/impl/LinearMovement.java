package gfight.world.movement.impl;

import gfight.common.api.Vect;
import gfight.common.impl.VectorImpl;

/**
 * An implementation of BaseMovement that returns a linear or linear accelerated Movement.
 */
public final class LinearMovement extends BaseMovement {
    private final double acceleration;

    /**
     * 
     * @param direction of the vector
     */
    public LinearMovement(final Vect direction) {
        this.setDirection(direction);
        acceleration = 1;
    }

    /**
     * 
     * @param acceleration of the vector
     * @param direction of the vector
     */
    public LinearMovement(final double acceleration, final Vect direction) {
        this.setDirection(direction);
        this.acceleration = acceleration;
    }

    @Override
    public void update() {
        if (acceleration != 1) {
            this.setDirection(new VectorImpl(acceleration, getDirection()));
        }
    }

}

package gfight.world.movement.impl;

import gfight.common.api.Vect;
import gfight.common.impl.VectorImpl;

/**
 * An implementation of BaseMovement that returns a linear or linear accelerated Movement.
 */
public final class LinearMovement extends BaseMovement {
    private final double accelleration;

    /**
     * 
     * @param direction of the vector
     */
    public LinearMovement(final Vect direction) {
        setDirection(direction);
        accelleration = 1;
    }

    /**
     * 
     * @param accelleration of the vector
     * @param direction of the vector
     */
    public LinearMovement(final double accelleration, final Vect direction) {
        setDirection(direction);
        this.accelleration = accelleration;
    }

    @Override
    public void update() {
        if (accelleration != 1) {
            setDirection(new VectorImpl(accelleration, getDirection()));
        }
    }

}

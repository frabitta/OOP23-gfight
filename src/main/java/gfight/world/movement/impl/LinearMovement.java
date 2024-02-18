package gfight.world.movement.impl;

import gfight.common.api.Vect;

/**
 * An implementation of BaseMovement that returns a linear or linear accelerated Movement.
 */
public final class LinearMovement extends BaseMovement {

    /**
     * 
     * @param direction of the vector
     */
    public LinearMovement(final Vect direction) {
        this.setDirection(direction);
    }

    @Override
    public void update() {
    }

}

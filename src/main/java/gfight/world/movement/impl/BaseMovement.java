package gfight.world.movement.impl;

import gfight.common.api.Vect;
import gfight.world.movement.api.Movement;

/**
 * An abstract implementation of Movement interface.
 */
public abstract class BaseMovement implements Movement {
    private Vect dirVector;

    @Override
    public final Vect getDirection() {
        return dirVector;
    }

    @Override
    public final void setDirection(final Vect direction) {
        dirVector = direction;
    }
}

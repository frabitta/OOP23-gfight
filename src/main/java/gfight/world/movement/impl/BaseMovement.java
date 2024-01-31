package gfight.world.movement.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.world.movement.api.Movement;

/**
 * An abstract implementation of Movement interface.
 */
public abstract class BaseMovement implements Movement {
    private Vector2D dirVector;

    @Override
    public final Vector2D getDirection() {
        return new Vector2D(1, dirVector);
    }

    @Override
    public final void setDirection(final Vector2D direction) {
        dirVector = new Vector2D(1, direction);
    }
}

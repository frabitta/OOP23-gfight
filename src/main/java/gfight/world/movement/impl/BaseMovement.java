package gfight.world.movement.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.world.movement.api.Movement;

public abstract class BaseMovement implements Movement{
    protected Vector2D dirVector;

    @Override
    public Vector2D getDirection() {
        return new Vector2D(1, dirVector);
    }

    @Override
    public void setDirection(Vector2D direction) {
        dirVector = new Vector2D(1, direction);
    }
}

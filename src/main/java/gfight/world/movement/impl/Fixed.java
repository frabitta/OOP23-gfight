package gfight.world.movement.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * An implementation of Movemnt that can be used to give a fixed status on
 * object that can move on some situation.
 */
public final class Fixed extends BaseMovement {

    /**
     * Fixed class constructor.
     */
    public Fixed() {
        setDirection(new Vector2D(0, 0));
    }

    @Override
    public void update() {

    }

}

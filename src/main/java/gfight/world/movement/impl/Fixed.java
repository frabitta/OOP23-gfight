package gfight.world.movement.impl;

import gfight.common.impl.VectorImpl;

/**
 * An implementation of Movemnt that can be used to give a fixed status on
 * object that can move on some situation.
 */
public final class Fixed extends BaseMovement {

    /**
     * Fixed class constructor.
     */
    public Fixed() {
        this.setDirection(new VectorImpl(0, 0));
    }

    @Override
    public void update() {

    }

}

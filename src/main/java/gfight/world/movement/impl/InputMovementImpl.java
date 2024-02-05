package gfight.world.movement.impl;

import gfight.common.api.Vect;
import gfight.common.impl.VectorImpl;
import gfight.world.movement.api.InputMovement;

/**
 * Implementation of InputMovement.
 */
public final class InputMovementImpl extends BaseMovement implements InputMovement {
    private Vect inputVector = new VectorImpl(0, 0);

    @Override
    public void update() {
        if (!this.inputVector.equals(new VectorImpl(0, 0))) {
            this.setDirection(this.inputVector.norm());
        } else {
            this.setDirection(this.inputVector);
        }
    }

    @Override
    public void addDirection(final Directions dir) {
        this.inputVector = this.inputVector.sum(getInputDirection(dir));
    }

    @Override
    public void removeDirection(final Directions dir) {
        this.inputVector = this.inputVector.sum(getInputDirection(dir).revert());
    }

    private Vect getInputDirection(final Directions dir) {
        switch (dir) {
            case EAST:
                return new VectorImpl(0, 1);

            case WEST:
                return new VectorImpl(0, -1);

            case SOUTH:
                return new VectorImpl(1, 0);

            case NORTH:
                return new VectorImpl(-1, 0);

            default:
                return new VectorImpl(0, 0);
        }
    }
}

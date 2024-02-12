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
        return switch (dir) {
            case SOUTH -> new VectorImpl(0, 1);
            case NORTH -> new VectorImpl(0, -1);
            case EAST -> new VectorImpl(1, 0);
            case WEST -> new VectorImpl(-1, 0);
            default -> new VectorImpl(0, 0);
        };
    }

    @Override
    public void setNullDirection() {
        this.inputVector = new VectorImpl(0, 0);
    }
}

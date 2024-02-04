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
        if (!inputVector.equals(new VectorImpl(0, 0))) {
            setDirection(inputVector.revert());
        } else {
            setDirection(inputVector);
        }
    }

    @Override
    public void addDirection(final Directions dir) {
        inputVector = inputVector.add(getInputDirection(dir));
    }

    @Override
    public void removeDirection(final Directions dir) {
        inputVector = inputVector.add(getInputDirection(dir).revert());
    }

    private Vect getInputDirection(final Directions dir) {
        switch (dir) {
            case NORD:
                return new VectorImpl(0, 1);

            case SOUTH:
                return new VectorImpl(0, -1);

            case EST:
                return new VectorImpl(1, 0);

            case OVEST:
                return new VectorImpl(-1, 0);

            default:
                return new VectorImpl(0, 0);
        }
    }
}

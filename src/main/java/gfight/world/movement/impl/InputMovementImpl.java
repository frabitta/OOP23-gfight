package gfight.world.movement.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import gfight.world.movement.api.InputMovement;

/**
 * Implementation of InputMovement.
 */
public final class InputMovementImpl extends BaseMovement implements InputMovement {
    private Vector2D inputVector = new Vector2D(0, 0);

    @Override
    public void update() {
        System.err.println(inputVector);
        if (!inputVector.equals(new Vector2D(0, 0))) {
            setDirection(inputVector.normalize());
        } else {
            setDirection(inputVector);
        }
    }

    @Override
    public void addDirection(final Directions dir) {
        System.err.println(getInputDirection(dir));
        inputVector = inputVector.add(getInputDirection(dir));
    }

    @Override
    public void removeDirection(final Directions dir) {
        inputVector = inputVector.add(getInputDirection(dir).negate());
    }

    private Vector2D getInputDirection(final Directions dir) {
        switch (dir) {
            case NORD:
                return new Vector2D(0, 1);

            case SOUTH:
                return new Vector2D(0, -1);

            case EST:
                return new Vector2D(1, 0);

            case OVEST:
                return new Vector2D(-1, 0);

            default:
                return new Vector2D(0, 0);
        }
    }
}

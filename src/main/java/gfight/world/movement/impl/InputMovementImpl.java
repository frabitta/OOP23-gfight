package gfight.world.movement.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import gfight.world.movement.api.InputMovement;

/**
 * Implementation of InputMovement.
 */
public final class InputMovementImpl extends BaseMovement implements InputMovement {

    @Override
    public void update() {
    }

    @Override
    public void addDirection(final Directions dir) {
        setDirection(getDirection().add(getInputDirection(dir)));
    }

    @Override
    public void removeDirection(final Directions dir) {
        setDirection(getDirection().add(getInputDirection(dir).negate()));

    }

    private Vector2D getInputDirection(final Directions dir) {
        switch (dir) {
            case NORD:
                return new Vector2D(0, -1);

            case SOUTH:
                return new Vector2D(0, 1);

            case EST:
                return new Vector2D(-1, 0);

            case OVEST:
                return new Vector2D(1, 0);

            default:
                return new Vector2D(0, 0);
        }
    }
}

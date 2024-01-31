package gfight.world.movement.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.movement.api.MovementFactory;

/**
 * Implementation of the Movement Factory.
 */
public final class MovementFactoryImpl implements MovementFactory {

    @Override
    public IabfsMovement createIabfsMovement(final MovingEntity agent, final GameEntity target) {
        return new IabfsMovement(agent, target);
    }

    @Override
    public LinearMovement createLinearMovement(final Vector2D direction) {
        return new LinearMovement(direction);
    }

    @Override
    public RandomMovement createRandomMovement() {
        return new RandomMovement();
    }

    @Override
    public Fixed createFixed() {
        return new Fixed();
    }

    @Override
    public LinearMovement createLinearMovement(final double accelleration, final Vector2D direction) {
        return new LinearMovement(accelleration, direction);
    }
}

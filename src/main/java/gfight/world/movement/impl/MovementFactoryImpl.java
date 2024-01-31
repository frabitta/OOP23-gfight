package gfight.world.movement.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.movement.api.MovementFactory;

public class MovementFactoryImpl implements MovementFactory {

    @Override
    public IabfsMovement createIabfsMovement(MovingEntity agent, GameEntity target) {
        return new IabfsMovement(agent, target);
    }

    @Override
    public LinearMovement createLinearMovement(Vector2D direction) {
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
    
}

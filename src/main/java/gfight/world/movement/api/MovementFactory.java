package gfight.world.movement.api;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.movement.impl.IabfsMovement;
import gfight.world.movement.impl.LinearMovement;
import gfight.world.movement.impl.RandomMovement;
import gfight.world.movement.impl.Fixed;

public interface MovementFactory {
    IabfsMovement createIabfsMovement(MovingEntity agent, GameEntity target);

    LinearMovement createLinearMovement(Vector2D direction);

    RandomMovement createRandomMovement();

    Fixed createFixed();
}
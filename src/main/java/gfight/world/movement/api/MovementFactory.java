package gfight.world.movement.api;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.world.api.GameEntity;
import gfight.world.movement.impl.IabfsMovement;
import gfight.world.movement.impl.LinearMovement;
import gfight.world.movement.impl.RandomMovement;
import gfight.world.movement.impl.Fixed;

/**
 * Factory for the creation of movements.
 */
public interface MovementFactory {

    /**
     * Creates the AI BFS based movement.
     * 
     * @param target
     * @return the AI BFS based movement object
     */
    IabfsMovement createIabfsMovement(GameEntity target);

    /**
     * Creates Linear movement.
     * 
     * @param direction
     * @return the Linear Movement Class object
     */
    LinearMovement createLinearMovement(Vector2D direction);

    /**
     * Creates Linear movement with an acceleration.
     * 
     * @param direction
     * @param accelleration
     * @return the Linear Movement Class object
     */
    LinearMovement createLinearMovement(double accelleration, Vector2D direction);

    /**
     * 
     * @return the random Movement class object
     */
    RandomMovement createRandomMovement();

    /**
     * 
     * @return the Fixed Movement class object
     */
    Fixed createFixed();
}

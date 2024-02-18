package gfight.world.movement.api;

import gfight.common.api.Vect;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.GameEntity;
import gfight.world.map.api.GameMap;
import gfight.world.movement.impl.BfsMovement;
import gfight.world.movement.impl.LinearMovement;

/**
 * Factory for the creation of movements.
 */
public interface MovementFactory {

    /**
     * Creates the AI BFS based movement.
     * 
     * @param target is the entity that you want to reach
     * @param agent  is the entity with the movement
     * @param map    the map of the game
     * @param speed  the speed of the agent
     * @return the BFS based movement object
     */
    BfsMovement createBfsMovement(GameEntity target, Character agent, GameMap map, double speed);

    /**
     * Creates Linear movement.
     * 
     * @param direction direction of the entity.
     * @return the Linear Movement Class object.
     */
    LinearMovement createLinearMovement(Vect direction);

    /**
     * 
     * @return an InputBasedMovement
     */
    InputMovement createInput();
}

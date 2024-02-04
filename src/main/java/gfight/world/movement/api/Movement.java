package gfight.world.movement.api;

import gfight.common.api.Vect;

/**
 * Interface for update direction based on the type of movement.
 */
public interface Movement {
    /**
     * it updates the vector based on new information.
     */
    void update();

    /**
     * 
     * @return the direction vector.
     */
    Vect getDirection();

    /**
     * it set the direction of the direction vector.
     * 
     * @param direction you want to set
     */
    void setDirection(Vect direction);
}

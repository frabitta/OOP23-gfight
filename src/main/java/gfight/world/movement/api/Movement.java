package gfight.world.movement.api;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

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
    Vector2D getDirection();

    /**
     * it set the direction of the direction vector.
     * 
     * @param direction you want to set
     */
    void setDirection(Vector2D direction);
}

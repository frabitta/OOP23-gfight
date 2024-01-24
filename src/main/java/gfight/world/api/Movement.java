package gfight.world.api;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public interface Movement {
    /**
     * it updates the vector based on new information
     */
    void update();

    /**
     * 
     * @return the dirrection vector
     */
    Vector2D getDirection();

    /**
     * it set the direction of the direction vector
     */
    void setDirection(Vector2D direction);
}//TODO input extends movement

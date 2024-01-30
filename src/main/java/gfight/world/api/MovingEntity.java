package gfight.world.api;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public interface MovingEntity extends CachedGameEntity {
    /**
     * 
     * @return the actual direction of the object
     */
    Vector2D getDirection();

    /**
     * it imposes the direction to the object
     * @param direction
     */
    void setDirection(Vector2D direction);

    /**
     * it update the data of the object following new position and the collisions
     */
    void updatePos(long deltaTime);
}

package gfight.world.api;

import java.util.Optional;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.world.movement.api.Movement;

/**
 * An interface for all game entity that also move.
 */
public interface MovingEntity extends CachedGameEntity {
    /**
     * 
     * @return the actual direction of the object.
     */
    Vector2D getDirection();

    /**
     * it imposes the direction to the object.
     * @param direction
     */
    void setDirection(Vector2D direction);

   /**
    * it update the data of the object following new position and the collisions.
    * @param deltaTime
    */
    void updatePos(long deltaTime);

    /**
     * Set the movement of the entity.
     * @param movement
     */
    void setMovement(Optional<Movement> movement);
}

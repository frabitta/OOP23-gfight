package gfight.world.entity.api;

import java.util.Optional;
import java.util.Set;

import gfight.common.api.Vect;
import gfight.world.movement.api.Movement;

/**
 * An interface for all game entity that also move.
 */
public interface MovingEntity extends CachedGameEntity {
    /**
     * 
     * @return the actual direction of the object.
     */
    Vect getDirection();

    /**
     * it imposes the direction to the object.
     * @param direction
     */
    void setDirection(Vect direction);

   /**
    * it update the data of the object following new position and the collisions.
    * @param deltaTime
    * @param gameobjects all games object to aplly collisions
    */
    void updatePos(long deltaTime, Set<GameEntity> gameobjects);

    /**
     * Set the movement of the entity.
     * @param movement
     */
    void setMovement(Optional<Movement> movement);
}

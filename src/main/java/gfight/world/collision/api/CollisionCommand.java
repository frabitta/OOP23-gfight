package gfight.world.collision.api;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;

/**
 * This interface can be used to handle a collision between to entities.
 * 
 * @param <ME> is the entitiy that moves and causes the collision
 * @param <GE> is the other entity
 */
public interface CollisionCommand<ME extends MovingEntity, GE extends GameEntity> {
    /**
     * Method to do the changes in entities for the collision.
     */
    void execute();
}

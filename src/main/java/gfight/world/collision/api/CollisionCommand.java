package gfight.world.collision.api;

import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;

/**
 * This interface can be used to handle a collision between to entities.
 * 
 * @param <M> is the entitiy that moves and causes the collision
 * @param <G> is the other entity
 */
public interface CollisionCommand<M extends MovingEntity, G extends GameEntity> {
    /**
     * Method to do the changes in entities for the collision.
     */
    void execute();
}

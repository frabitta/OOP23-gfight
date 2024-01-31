package gfight.world.api;

import gfight.world.impl.ActiveEntityImpl;
import gfight.world.movement.api.Movement;

/**
 * This interface represents the concept of a EntityBuilder.
 */
public interface EntityBuilder {
    /**
     * Adds the movement.
     * @param movement
     * @return an EntityBuilder object
     */
    EntityBuilder addMovement(Movement movement);

    /**
     * 
     * @return an ActiveEntityImpl calling its constructor
     * @throws IllegalStateException
     */
    ActiveEntityImpl build() throws IllegalStateException;
}

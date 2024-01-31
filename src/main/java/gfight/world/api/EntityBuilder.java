package gfight.world.api;

import gfight.world.impl.ActiveEntityImpl;
import gfight.world.movement.api.Movement;

/**
 * This interface represents the concept of a EntityBuilder.
 */
public interface EntityBuilder {
    /**
     * Adds the movement and returns an EntityBuilder object.
     * @param movement
     * @return
     */
    EntityBuilder addMovement(Movement movement);

    /**
     * Returns an ActiveEntityImpl calling its constructor.
     * @return
     * @throws IllegalStateException
     */
    ActiveEntityImpl build() throws IllegalStateException;
}

package gfight.engine.graphics.api;

import gfight.common.Position2D;

/**
 * Interface of a camera that's able to move in space.
 */
public interface MovableCamera {

    /**
     * Moves the camera to the new position.
     * @param position
     */
    void moveTo(Position2D position);

}

package gfight.world;

import gfight.engine.graphics.api.MovableCamera;

/**
 * Interface for a class able to move a Camera.
 */
public interface CameraMover {
    /**
     * Setup a new Camera.
     * @param camera
     */
    void installCamera(MovableCamera camera);
}

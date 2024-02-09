package gfight.world;

import gfight.engine.graphics.api.WorldCamera;

/**
 * Interface for a class able to move a Camera.
 */
public interface CameraMover {
    /**
     * Setup a new Camera.
     * @param camera
     */
    void installCamera(WorldCamera camera);
}

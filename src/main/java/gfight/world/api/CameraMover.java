package gfight.world.api;

import gfight.engine.graphics.api.WorldCamera;

/**
 * Interface for a class able to move a Camera.
 */
public interface CameraMover {
    /**
     * Setup a new Camera.
     * @param camera WorldCamera to install
     */
    void installCamera(WorldCamera camera);
}

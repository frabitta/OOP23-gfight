package gfight.world;

import gfight.engine.graphics.api.MovableCamera;

public interface CameraMover {
    /**
     * Setup a new Camera
     * @param camera
     */
    void installCamera(MovableCamera camera);
}

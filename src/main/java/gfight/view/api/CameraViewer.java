package gfight.view.api;

import gfight.engine.graphics.api.ViewCamera;

/**
 * Interface for a class able to "see" through a Camera.
 */
public interface CameraViewer {
    /**
     * Setup a new Camera.
     * @param camera ViewCamera to install
     */
    void installCamera(ViewCamera camera);
}

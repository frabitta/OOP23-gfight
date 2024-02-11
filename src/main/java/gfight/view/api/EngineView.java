package gfight.view.api;

import java.util.List;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewCamera;

/**
 * A viewer for the engine.
 */
public interface EngineView {

    /**
     * Initialize the view.
     * @param camera ViewCamera that the view can use to print correctly on screen
     */
    void initialize(ViewCamera camera);

    /**
     * Renders a frame.
     * @param gComponentsList list of GraphicsComponents to print
     */
    void render(List<GraphicsComponent> gComponentsList);

}

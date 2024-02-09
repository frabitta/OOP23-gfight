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
     * @param camera
     */
    void initialize(ViewCamera camera);

    /**
     * Renders a frame.
     * @param gComponentsList
     */
    void render(List<GraphicsComponent> gComponentsList);

}

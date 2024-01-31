package gfight.view.api;

import java.util.List;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;

/**
 * A viewer for the engine.
 */
public interface EngineView {

    /**
     * Initialize the view.
     * @param camera
     */
    void initialize(ViewableCamera camera);

    /**
     * Renders a frame.
     * @param gComponentsList
     */
    void render(List<GraphicsComponent> gComponentsList);

}

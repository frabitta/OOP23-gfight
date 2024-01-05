package gfight.view;

import java.util.List;

/**
 * A viewer for the engine.
 */
public interface EngineView {

    /**
     * Initialize the view.
     */
    void initialize();

    /**
     * Renders a frame.
     * @param gComponentsList
     */
    void render(List<GraphicsComponent> gComponentsList);
}

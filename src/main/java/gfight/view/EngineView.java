package gfight.view;

import java.util.List;

/**
 * A viewer for the engine.
 */
public interface EngineView{

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

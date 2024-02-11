package gfight.view.api;

import java.awt.Graphics2D;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewCamera;

/**
 * A renderer of a specific GraphicsComponent.
 */
public interface GraphicsComponentRenderer {

    /**
     * Renders the GraphicsComponent.
     * @param g         Graphics2D used to print
     * @param camera    ViewCamera used to filter positions
     */
    void render(Graphics2D g, ViewCamera camera);

    /**
     * A setter of the referenced GraphicsComponent.
     * @param gComp GraphicsComponent that the renderer prints
     */
    void setComponent(GraphicsComponent gComp);

}

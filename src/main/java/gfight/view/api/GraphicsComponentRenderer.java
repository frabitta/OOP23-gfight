package gfight.view.api;

import java.awt.Graphics2D;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;

/**
 * A renderer of a specific GraphicsComponent.
 */
public interface GraphicsComponentRenderer {

    /**
     * Renders the GraphicsComponent.
     * @param g
     * @param camera
     */
    void render(Graphics2D g, ViewableCamera camera);

    /**
     * A setter of the referenced GraphicsComponent.
     * @param gComp
     */
    void setComponent(GraphicsComponent gComp);

}

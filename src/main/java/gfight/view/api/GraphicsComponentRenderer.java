package gfight.view.api;

import java.awt.Graphics2D;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;

public interface GraphicsComponentRenderer {

    /**
     * Renders the GraphicsComponent
     * @param g
     * @param camera
     */
    public void render(final Graphics2D g, final ViewableCamera camera);

    public void setComponent(final GraphicsComponent gComp);

}

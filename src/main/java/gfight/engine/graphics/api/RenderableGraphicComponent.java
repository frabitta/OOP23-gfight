package gfight.engine.graphics.api;

import gfight.view.api.GraphicsComponentRenderer;

/**
 * Interface of a graphics component that has a GraphicsComponentRenderer.
 */
public interface RenderableGraphicComponent {

    /**
     * 
     * @return renderer for this graphicsComponent.
     */
    GraphicsComponentRenderer getRenderer();

}

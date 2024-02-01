package gfight.world;

import java.util.List;

import gfight.engine.graphics.api.GraphicsComponent;

/**
 * Interface to create a world (model part of the application).
 */
public interface World extends CameraMover {
    /**
     * Initializes the world.
     */
    void initialize();

    /**
     * Update the world.
     * @param deltaTime
     */
    void update(long deltaTime);

    /**
     * @return list of the GraphicsComponents to render.
     */
    List<GraphicsComponent> getGraphicsComponents();
}

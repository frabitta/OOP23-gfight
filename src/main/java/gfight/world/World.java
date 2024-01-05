package gfight.world;

import java.util.List;

import gfight.view.GraphicsComponent;

/**
 * Interface to create a world (model part of the application).
 */
public interface World {
    /**
     * Instantiate the world.
     */
    void instantiate();

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

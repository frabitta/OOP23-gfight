package gfight.world;

import java.util.List;
import java.util.Queue;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.input.api.InputEvent;

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

    /**
     * Process input events coming from the engine.
     * @param inputEvents
     */
    void processInput(Queue<InputEvent> inputEvents);
}

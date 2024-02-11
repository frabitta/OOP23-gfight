package gfight.world;

import java.util.List;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.input.api.InputEvent;

/**
 * Interface to create a world (model part of the application).
 */
public interface World extends CameraMover {

    /*
     * Returns {@code true} if the game is over.
     */
    boolean isOver();

    /**
     * Update the world based on the time passed.
     * 
     * @param deltaTime
     */
    void update(long deltaTime);

    /**
     * @return list of the GraphicsComponents to render.
     */
    List<GraphicsComponent> getGraphicsComponents();

    /**
     * Process input events coming from the engine.
     * 
     * @param event the event that needs to be processed
     */
    void processInput(InputEvent event);
}

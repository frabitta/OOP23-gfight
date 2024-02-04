package gfight.engine.input.api;

import gfight.common.api.Position2D;

/**
 * Input event to describe events from the mouse.
 */
public interface InputEventMouse extends InputEvent {

    /**
     * Returns the position 2D in relation to the world. (needs to be trasformed with the help of the Camera).
     * @return Position2D world-related 
     */
    Position2D getPosition();
}

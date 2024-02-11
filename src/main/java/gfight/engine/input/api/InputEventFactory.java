package gfight.engine.input.api;

import gfight.common.api.Position2D;

/**
 * Factory of InputEvents.
 */
public interface InputEventFactory {

    /**
     * Returns a new InputEvent that describes a pressed key.
     * @param key key pressed
     * @return InputEvent describing the pressed key
     */
    InputEvent pressedKey(int key);

    /**
     * Returns a new InputEvent that describes a released key.
     * @param key key released
     * @return InputEvent describing the released key
     */
    InputEvent releasedKey(int key);

    /**
     * Returns a new InputEvent that describes the mouse at a certain position with a button pressed.
     * @param position screen position of the mouse
     * @return InputEvent describing the pressed key
     */
    InputEvent mouseDownAtPosition(Position2D position);

    /**
     * Returns a new InputEvent that describes the mouse at a certain position without any button pressed.
     * @param position screen position of the mouse
     * @return InputEvent describing the pressed key
     */
    InputEvent mouseUpAtPosition(Position2D position);
}

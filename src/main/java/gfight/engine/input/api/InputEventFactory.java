package gfight.engine.input.api;

import gfight.common.Position2D;

/**
 * Factory of InputEvents.
 */
public interface InputEventFactory {

    /**
     * Returns a new InputEvent that describes a pressed key.
     * @param key
     * @return InputEvent describing the pressed key
     */
    InputEvent pressedKey(int key);

    /**
     * Returns a new InputEvent that describes a released key.
     * @param key
     * @return InputEvent describing the released key
     */
    InputEvent releasedKey(int key);

    /**
     * Returns a new InputEvent that describes the mouse clicked at a certain position.
     * @param key
     * @return InputEvent describing the pressed key
     */
    InputEvent mouseDownAtPosition(Position2D position);
}

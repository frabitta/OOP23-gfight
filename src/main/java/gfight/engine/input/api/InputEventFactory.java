package gfight.engine.input.api;

import gfight.common.api.Position2D;
import gfight.engine.input.api.InputEventValue.Value;

import java.util.Optional;

/**
 * Factory of InputEvents.
 */
public interface InputEventFactory {

    /**
     * Given a pressed key filters it.
     * @param key pressed key
     * @return the corrispondi Value if present
     */
    Optional<Value> filterKeyValue(int key);

    /**
     * Returns a new InputEvent that describes a pressed key.
     * @param value key pressed
     * @return InputEvent describing the pressed key
     */
    InputEvent pressedValue(Value value);

    /**
     * Returns a new InputEvent that describes a released key.
     * @param value key released
     * @return InputEvent describing the released key
     */
    InputEvent releasedValue(Value value);

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

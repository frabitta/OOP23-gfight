package gfight.engine.input.api;

/**
 * Input event to describe events from the keyboard.
 */
public interface InputEventKey extends InputEvent {

    /**
     * Returns the key pressed on the keyboard.
     * @return char pressed
     */
    int getKey();
}

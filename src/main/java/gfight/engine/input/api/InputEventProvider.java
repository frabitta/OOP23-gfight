package gfight.engine.input.api;

/**
 * Interface for input event providers.
 */
public interface InputEventProvider {

    /**
     * Set the input event listener.
     * @param listener a listener for input events
     */
    void setInputEventListener(InputEventListener listener);
}

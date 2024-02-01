package gfight.engine.input.api;

/**
 * Interface for a class able to receive and andle InputEvents.
 */
public interface InputEventListener {

    /**
     * Notify the listener with a new event.
     * @param event
     */
    void notifyInputEvent(InputEvent event);

}

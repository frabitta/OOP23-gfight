package gfight.engine.input.api;

/**
 * Interface of a generic Input event.
 */
public interface InputEvent {

    /**
     * Type of input event.
     */
    enum Type {
        /** Started pressing the "button".*/
        PRESSED,
        /** Released the "button".*/
        RELEASED,
        /** Holding the mouse button pressed.*/
        MOUSE_DOWN,
        /** Mouse button is not pressed.*/
        MOUSE_UP
    }

    /**
     * Returns the type of the event.
     * @return Type of event
     */
    Type getType();
}

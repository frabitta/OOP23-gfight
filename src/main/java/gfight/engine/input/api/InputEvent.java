package gfight.engine.input.api;

/**
 * Interface of a generic Input event.
 */
public interface InputEvent {

    /**
     * Type of input event.
     */
    enum Type {
        /** Started pressing the "button"*/
        PRESSED,
        /** Holding the "button" pressed */
        HOLDING,
        /** Released the "button" */
        RELEASED
    }

    /**
     * Returns the type of the event.
     * @return Type of event
     */
    Type getType();
}

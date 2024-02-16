package gfight.engine.input.api;

/**
 * Interface of a generic Input event.
 */
public interface InputEvent {

    /**
     * Type of input event.
     */
    enum Type {
        /** This inputEvent is now active.*/
        ACTIVE,
        /** This inputEvent is now inactive.*/
        INACTIVE
    }

    /**
     * Returns the type of the event.
     * @return Type of event
     */
    Type getType();
}

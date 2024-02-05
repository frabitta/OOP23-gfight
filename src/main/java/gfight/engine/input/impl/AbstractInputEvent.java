package gfight.engine.input.impl;

import java.util.Objects;

import gfight.engine.input.api.InputEvent;

/**
 * Abstract Input Event class.
 * Has the implementation of the getType method.
 */
public abstract class AbstractInputEvent implements InputEvent {

    private final Type type;

    AbstractInputEvent(final Type type) {
        this.type = type;
    }

    @Override
    public final Type getType() {
        return this.type;
    }

    /**
     * Redefine your own and remember to call this with super() to check. 
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AbstractInputEvent) {
            final var e = (AbstractInputEvent) obj;
            return this.getType() == e.getType();
        }
        return false;
    }

    /**
     * Redefine your own and remember to call this with super() to check. 
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }
}

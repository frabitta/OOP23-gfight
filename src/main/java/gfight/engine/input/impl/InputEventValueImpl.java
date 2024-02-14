package gfight.engine.input.impl;

import java.util.Objects;

import gfight.engine.input.api.InputEventValue;

/**
 * Implementation of InputEvent to describe the pressing of buttons.
 * (for example for the keyboard).
 */
public final class InputEventValueImpl extends AbstractInputEvent implements InputEventValue {

    private final Value key;

    InputEventValueImpl(final Type type, final Value key) {
        super(type);
        this.key = key;
    }

    @Override
    public Value getValue() {
        return this.key;
    }

    /**
     * Equals if of the same class, type and same key. 
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InputEventValueImpl) {
            final var e = (InputEventValueImpl) obj;
            return super.equals(obj) && this.getValue() == e.getValue();
        }
        return false;
    }

    /**
     * Generates hashCode using type and key.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getValue());
    }
}

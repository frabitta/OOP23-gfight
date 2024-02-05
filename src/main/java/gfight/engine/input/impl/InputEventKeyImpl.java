package gfight.engine.input.impl;

import java.util.Objects;

import gfight.engine.input.api.InputEventKey;

/**
 * Implementation of InputEvent to describe the pressing of buttons.
 * (for example for the keyboard).
 */
public class InputEventKeyImpl extends AbstractInputEvent implements InputEventKey {

    private final int key;

    InputEventKeyImpl(final Type type, final int key) {
        super(type);
        this.key = key;
    }

    @Override
    public final int getKey() {
        return this.key;
    }

    /**
     * Equals if of the same class, type and same key. 
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InputEventKeyImpl) {
            final var e = (InputEventKeyImpl) obj;
            return super.equals(obj) && this.getKey() == e.getKey();
        }
        return false;
    }

    /**
     * Generates hashCode using type and key.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), this.getKey());
    }
}

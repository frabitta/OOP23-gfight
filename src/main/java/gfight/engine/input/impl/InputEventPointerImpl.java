package gfight.engine.input.impl;

import java.util.Objects;

import gfight.common.api.Position2D;
import gfight.engine.input.api.InputEventPointer;

/**
 * Implementation of InputEvent to describe the mouse position.
 */
public final class InputEventPointerImpl extends AbstractInputEvent implements InputEventPointer {

    private final Position2D position;

    InputEventPointerImpl(final Type type, final Position2D position) {
        super(type);
        this.position = position;
    }

    @Override
    public Position2D getPosition() {
        return this.position;
    }

    /**
     * Equals if of the same class, type and same position. 
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InputEventPointerImpl) {
            final var e = (InputEventPointerImpl) obj;
            return super.equals(obj) && this.getPosition().equals(e.getPosition());
        }
        return false;
    }

    /**
     * Generates hashCode using type and position.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getPosition());
    }
}

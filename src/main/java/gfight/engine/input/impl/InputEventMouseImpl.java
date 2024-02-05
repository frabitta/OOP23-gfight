package gfight.engine.input.impl;

import java.util.Objects;

import gfight.common.api.Position2D;
import gfight.engine.input.api.InputEventMouse;

/**
 * Implementation of InputEvent to describe the mouse position.
 */
public class InputEventMouseImpl extends AbstractInputEvent implements InputEventMouse {

    private final Position2D position;

    InputEventMouseImpl(final Type type, final Position2D position) {
        super(type);
        this.position = position;
    }

    @Override
    public final Position2D getPosition() {
        return this.position;
    }

    /**
     * Equals if of the same class, type and same position. 
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InputEventMouseImpl) {
            final var e = (InputEventMouseImpl) obj;
            return super.equals(obj) && this.getPosition().equals(e.getPosition());
        }
        return false;
    }

    /**
     * Generates hashCode using type and position.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), this.getPosition());
    }
}

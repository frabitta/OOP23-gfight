package gfight.engine.input.impl;

import java.util.Objects;

import gfight.common.api.Position2D;
import gfight.engine.input.api.InputEventMouse;

public class InputEventMouseImpl extends AbstractInputEvent implements InputEventMouse {

    private final Position2D position;

    InputEventMouseImpl(Type type, Position2D position) {
        super(type);
        this.position = position;
    }

    @Override
    public Position2D getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InputEventMouseImpl) {
            final var e = (InputEventMouseImpl) obj;
            return super.equals(obj) && this.getPosition().equals(e.getPosition());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getPosition());
    }
}

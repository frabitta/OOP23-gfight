package gfight.engine.input.impl;

import java.util.Objects;

import gfight.engine.input.api.InputEventKey;

public class InputEventKeyImpl extends AbstractInputEvent implements InputEventKey {

    private final int key;

    InputEventKeyImpl(Type type, int key) {
        super(type);
        this.key = key;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InputEventKeyImpl) {
            final var e = (InputEventKeyImpl) obj;
            return super.equals(obj) && this.getKey() == e.getKey();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getKey());
    }
}

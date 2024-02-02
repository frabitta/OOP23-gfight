package gfight.engine.input.impl;

import gfight.engine.input.api.InputEvent;

/**
 * Abstract Input Event class.
 * Has the implementation of the getType method.
 */
public abstract class AbstractInputEvent implements InputEvent{

    private final Type type;

    AbstractInputEvent(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return this.type;
    }
}

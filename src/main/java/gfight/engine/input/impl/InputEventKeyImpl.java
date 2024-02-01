package gfight.engine.input.impl;

import gfight.engine.input.api.InputEventKey;

public class InputEventKeyImpl extends AbstractInputEvent implements InputEventKey{

    private final int key;

    InputEventKeyImpl(Type type, int key) {
        super(type);
        this.key = key;
    }

    @Override
    public int getKey() {
        return this.key;
    }

}

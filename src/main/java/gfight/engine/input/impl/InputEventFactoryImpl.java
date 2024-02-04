package gfight.engine.input.impl;

import gfight.common.api.Position2D;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventFactory;
import gfight.engine.input.api.InputEvent.Type;

public class InputEventFactoryImpl implements InputEventFactory{

    @Override
    public InputEvent pressedKey(int key) {
        return new InputEventKeyImpl(Type.PRESSED, key);
    }

    @Override
    public InputEvent releasedKey(int key) {
        return new InputEventKeyImpl(Type.RELEASED, key);
    }

    @Override
    public InputEvent mouseDownAtPosition(Position2D position) {
        return new InputEventMouseImpl(Type.HOLDING, position);
    }

}

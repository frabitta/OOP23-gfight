package gfight.engine.input.impl;

import gfight.common.api.Position2D;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventFactory;
import gfight.engine.input.api.InputEvent.Type;

/**
 * Implementation of a factory of InputEvents.
 */
public class InputEventFactoryImpl implements InputEventFactory {

    @Override
    public final InputEvent pressedKey(final int key) {
        return new InputEventValueImpl(Type.PRESSED, key);
    }

    @Override
    public final InputEvent releasedKey(final int key) {
        return new InputEventValueImpl(Type.RELEASED, key);
    }

    @Override
    public final InputEvent mouseDownAtPosition(final Position2D position) {
        return new InputEventPointerImpl(Type.MOUSE_DOWN, position);
    }

    @Override
    public final InputEvent mouseUpAtPosition(final Position2D position) {
        return new InputEventPointerImpl(Type.MOUSE_UP, position);
    }

}

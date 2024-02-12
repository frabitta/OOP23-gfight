package gfight.engine.input.impl;

import gfight.common.api.Position2D;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventFactory;
import gfight.engine.input.api.InputEvent.Type;
import gfight.engine.input.api.InputEventValue.Value;

import java.util.stream.Stream;
import java.util.Optional;

/**
 * Implementation of a factory of InputEvents.
 */
public class InputEventFactoryImpl implements InputEventFactory {

    public Optional<Value> filterKeyValue(final int key) {
        final Optional<Value> outValue = Stream.of(Value.values()).filter(value -> (value.getKey() == key)).findFirst();
        return outValue;
    }

    @Override
    public final InputEvent pressedValue(final Value value) {
        return new InputEventValueImpl(Type.PRESSED, value);
    }

    @Override
    public final InputEvent releasedValue(final Value value) {
        return new InputEventValueImpl(Type.RELEASED, value);
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

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
public final class InputEventFactoryImpl implements InputEventFactory {

    @Override
    public Optional<Value> filterKeyValue(final int key) {
        return Stream.of(Value.values()).filter(value -> value.getKey() == key).findFirst();
    }

    @Override
    public InputEvent pressedValue(final Value value) {
        return new InputEventValueImpl(Type.ACTIVE, value);
    }

    @Override
    public InputEvent releasedValue(final Value value) {
        return new InputEventValueImpl(Type.INACTIVE, value);
    }

    @Override
    public InputEvent mouseDownAtPosition(final Position2D position) {
        return new InputEventPointerImpl(Type.ACTIVE, position);
    }

    @Override
    public InputEvent mouseUpAtPosition(final Position2D position) {
        return new InputEventPointerImpl(Type.INACTIVE, position);
    }

}

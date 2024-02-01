package gfight.engine.input.impl;

import gfight.common.Position2D;
import gfight.engine.input.api.InputEventMouse;

public class InputEventMouseImpl extends AbstractInputEvent implements InputEventMouse{

    private final Position2D position;

    InputEventMouseImpl(Type type, Position2D position) {
        super(type);
        this.position = position;
    }

    @Override
    public Position2D getPosition() {
        return this.position;
    }

}

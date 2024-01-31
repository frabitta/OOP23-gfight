package gfight.engine.graphics.impl;

import gfight.common.Pair;
import gfight.common.Position2D;
import gfight.engine.graphics.api.Camera;

public class CameraImpl implements Camera {

    private Position2D position;

    @Override
    public void moveTo(Position2D newPos) {
        this.position = newPos;
    }

    @Override
    public Position2D getRelativePosition(Position2D pos) {
        return new Pair(pos.getX()-position.getX(), pos.getY()-position.getY());
    }

}

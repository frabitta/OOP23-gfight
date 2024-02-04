package gfight.engine.graphics.impl;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.Camera;

/**
 * Implementation of a simple camera.
 */
public class CameraImpl implements Camera {

    private Position2D position = new Position2DImpl(0, 0);

    @Override
    public final void moveTo(final Position2D position) {
        this.position = position;
    }

    @Override
    public Position2D getScreenPosition(Position2D pos) {
        return new Position2DImpl(pos.getX() - position.getX(), pos.getY() - position.getY());
    }

    @Override
    public Position2D getWorldPosition(Position2D pos) {
        return new Position2DImpl(pos.getX() + position.getX(), pos.getY() + position.getY());
    }

}

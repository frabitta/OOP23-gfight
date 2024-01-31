package gfight.engine.graphics.impl;

import gfight.common.Pair;
import gfight.common.Position2D;
import gfight.engine.graphics.api.Camera;

/**
 * Implementation of a simple camera.
 */
public class CameraImpl implements Camera {

    private Position2D position = new Pair(0, 0);

    @Override
    public final void moveTo(final Position2D position) {
        this.position = position;
    }

    @Override
    public final Position2D getRelativePosition(final Position2D pos) {
        return new Pair(pos.getX() - position.getX(), pos.getY() - position.getY());
    }

}

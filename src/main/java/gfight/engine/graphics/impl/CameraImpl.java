package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.common.impl.VectorImpl;
import gfight.engine.graphics.api.Camera;
import gfight.engine.graphics.api.GraphicsComponent.GraphicType;

/**
 * Implementation of a simple camera.
 */
public class CameraImpl implements Camera {

    private Position2D cameraPos = new Position2DImpl(0, 0);

    @Override
    public final void moveTo(final Position2D cameraPos) {
        this.cameraPos = cameraPos;
    }

    @Override
    public final Position2D getWorldPosition(final Position2D pos) {
        return pos.sum(new VectorImpl(cameraPos.getX(), cameraPos.getY()));
        //return new Position2DImpl(pos.getX() + position.getX(), pos.getY() + position.getY());
    }

    @Override
    public Position2D getScreenPosition(Position2D pos, GraphicType type) {
        return pos.sum(new VectorImpl(-cameraPos.getX(), -cameraPos.getY()));
        //return new Position2DImpl(pos.getX() - position.getX(), pos.getY() - position.getY());
    }

    @Override
    public List<Position2D> getScreenPositions(List<Position2D> pos, GraphicType type) {
        return pos.stream().map(p -> getScreenPosition(p, type)).toList();
    }

}

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

    private final static double DEFAULT_X = 1920;
    private final static double DEFAULT_Y = 1080;
    private final static double DEFAULT_ARATIO = DEFAULT_Y / DEFAULT_X;

    private Position2D cameraPos = new Position2DImpl(0, 0);
    private double screenX = DEFAULT_X;
    private double screenY = DEFAULT_Y;
    private double sizeRatio;
    private double aspectRatio;
    private double offsetY;
    private double offsetX;

    @Override
    public final void moveTo(final Position2D cameraPos) {
        this.cameraPos = cameraPos;
    }

    @Override
    public final Position2D getWorldPosition(final Position2D pos) {
        return getWorldFromVirtual(getVirtualFromScreen(pos));
    }

    @Override
    public Position2D getScreenPosition(Position2D pos, GraphicType type) {
        final var virtualPos = type == GraphicType.WORLD ? getVirtualFromWorld(pos) : pos;
        return getScreenFromVirtual(virtualPos);
    }

    private Position2D getVirtualFromWorld(Position2D pos) {
        return pos.sum(new VectorImpl(-cameraPos.getX(), -cameraPos.getY()));
    }

    private Position2D getWorldFromVirtual(Position2D pos) {
        return pos.sum(new VectorImpl(cameraPos.getX(), cameraPos.getY()));
    }

    private Position2D getScreenFromVirtual(Position2D pos) {
        return new Position2DImpl(pos.getX() * this.sizeRatio + this.offsetX, pos.getY() * this.sizeRatio + this.offsetY);
    }

    private Position2D getVirtualFromScreen(Position2D pos) {
        return new Position2DImpl((pos.getX() - this.offsetX) / this.sizeRatio , (pos.getY() - this.offsetY) / this.sizeRatio);
    }

    @Override
    public List<Position2D> getScreenPositions(List<Position2D> pos, GraphicType type) {
        return pos.stream().map(p -> getScreenPosition(p, type)).toList();
    }

    @Override
    public void setScreenDimension(double width, double height) {
        this.screenX = width;
        this.screenY = height;
        this.aspectRatio = this.screenY / this.screenX;
        if (this.aspectRatio >= DEFAULT_ARATIO) {
            this.sizeRatio = this.screenX / DEFAULT_X;
            this.offsetX = 0;
            this.offsetY = (this.screenY - DEFAULT_Y * this.screenX / DEFAULT_X) / 2;
        } else {
            this.sizeRatio = this.screenY / DEFAULT_Y;
            this.offsetX = (this.screenX - DEFAULT_X * this.screenY / DEFAULT_Y) / 2;
            this.offsetY = 0;
        }
    }

    @Override
    public double getHoriOffset() {
        return this.offsetX;
    }

    @Override
    public double getVertOffset() {
        return this.offsetY;
    }

    @Override
    public double getSizeRatio() {
        return this.sizeRatio;
    }

}

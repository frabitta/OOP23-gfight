package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.common.impl.Position2DImpl;
import gfight.common.impl.VectorImpl;
import gfight.engine.graphics.api.Camera;
import gfight.engine.graphics.api.GraphicsComponent.GraphicType;

/**
 * Implementation of a simple camera.
 */
public final class CameraImpl implements Camera {

    private static final  double VIRTUAL_X = 500;
    private static final double VIRTUAL_Y = 500;
    private static final double VIRTUAL_ARATIO = VIRTUAL_Y / VIRTUAL_X;
    private final Position2D centerPos = new Position2DImpl(VIRTUAL_X / 2, VIRTUAL_Y / 2);

    private Position2D cameraPos = new Position2DImpl(0, 0);
    private double sizeRatio = 1;
    private double offsetX;
    private double offsetY;

    private boolean areaSetted;
    private double areaBorderX1;
    private double areaBorderX2;
    private double areaBorderY1;
    private double areaBorderY2;

    @Override
    public void moveTo(final Position2D cameraPos) {
        this.cameraPos = cameraPos;
    }

    @Override
    public Position2D getWorldPosition(final Position2D pos) {
        return getWorldFromVirtual(getVirtualFromScreen(pos));
    }

    @Override
    public Position2D getScreenPosition(final Position2D pos, final GraphicType type) {
        final var virtualPos = type == GraphicType.WORLD ? getVirtualFromWorld(pos) : pos;
        return getScreenFromVirtual(virtualPos);
    }

    private Position2D getVirtualFromWorld(final Position2D pos) {
        return pos.sum(new VectorImpl(-cameraPos.getX(), -cameraPos.getY()));
    }

    private Position2D getWorldFromVirtual(final Position2D pos) {
        return pos.sum(new VectorImpl(cameraPos.getX(), cameraPos.getY()));
    }

    private Position2D getScreenFromVirtual(final Position2D pos) {
        return new Position2DImpl(pos.getX() * this.sizeRatio + this.offsetX, pos.getY() * this.sizeRatio + this.offsetY);
    }

    private Position2D getVirtualFromScreen(final Position2D pos) {
        return new Position2DImpl((pos.getX() - this.offsetX) / this.sizeRatio, (pos.getY() - this.offsetY) / this.sizeRatio);
    }

    @Override
    public List<Position2D> getScreenPositions(final List<Position2D> pos, final GraphicType type) {
        return pos.stream().map(p -> getScreenPosition(p, type)).toList();
    }

    @Override
    public void setScreenDimension(final double width, final double height) {
        final double screenX = width;
        final double screenY = height;
        final double aspectRatio = screenY / screenX;
        if (aspectRatio >= VIRTUAL_ARATIO) {
            this.sizeRatio = screenX / VIRTUAL_X;
            this.offsetX = 0;
            this.offsetY = (screenY - VIRTUAL_Y * screenX / VIRTUAL_X) / 2;
        } else {
            this.sizeRatio = screenY / VIRTUAL_Y;
            this.offsetX = (screenX - VIRTUAL_X * screenY / VIRTUAL_Y) / 2;
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

    @Override
    public void centerOn(final Position2D position) {
        moveTo(position.sum(new VectorImpl(-centerPos.getX(), -centerPos.getY())));
    }

    @Override
    public void setArea(final int height, final int width) {
        this.areaBorderY1 = this.centerPos.getY() - height / 2;
        this.areaBorderY2 = this.centerPos.getY() + height / 2;
        this.areaBorderX1 = this.centerPos.getX() - width / 2;
        this.areaBorderX2 = this.centerPos.getX() + width / 2;
        this.areaSetted = true;
    }

    @Override
    public void keepInArea(final Position2D position) {
        if (this.areaSetted) {
            centerOn(position);
        } else {
            final Position2D virtualPos = getVirtualFromWorld(position);
            Vect movingVect = new VectorImpl(0, 0);
            if (virtualPos.getY() < this.areaBorderY1) {
                movingVect = movingVect.sum(new VectorImpl(0, virtualPos.getY() - this.areaBorderY1));
            }
            if (virtualPos.getY() > this.areaBorderY2) {
                movingVect = movingVect.sum(new VectorImpl(0, virtualPos.getY() - this.areaBorderY2));
            }
            if (virtualPos.getX() < this.areaBorderX1) {
                movingVect = movingVect.sum(new VectorImpl(virtualPos.getX() - this.areaBorderX1, 0));
            }
            if (virtualPos.getX() > this.areaBorderX2) {
                movingVect = movingVect.sum(new VectorImpl(virtualPos.getX() - this.areaBorderX2, 0));
            }
            moveTo(this.cameraPos.sum(movingVect));
        }
    }

}

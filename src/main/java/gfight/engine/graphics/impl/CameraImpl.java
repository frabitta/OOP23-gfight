package gfight.engine.graphics.impl;

import java.util.List;
import java.util.Optional;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.common.impl.Position2DImpl;
import gfight.common.impl.VectorImpl;
import gfight.engine.graphics.api.Camera;
import gfight.engine.graphics.api.GraphicsComponent.GraphicType;

/**
 * Implementation of a simple camera.
 */
public class CameraImpl implements Camera {

    private final static double DEFAULT_X = 500;
    private final static double DEFAULT_Y = 500;
    private final static double DEFAULT_ARATIO = DEFAULT_Y / DEFAULT_X;
    
    private final Position2D centerPos = new Position2DImpl(DEFAULT_X/2, DEFAULT_Y/2);

    private Position2D cameraPos = new Position2DImpl(0, 0);
    private double screenX = DEFAULT_X;
    private double screenY = DEFAULT_Y;
    private double sizeRatio = 1;
    private double aspectRatio  = 1;
    private double offsetY = 0;
    private double offsetX = 0;

    private Optional<Integer> areaHeight;
    private Optional<Integer> areaWidth;
    private double areaBorderY1;
    private double areaBorderY2;
    private double areaBorderX1;
    private double areaBorderX2;

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

    @Override
    public void centerOn(Position2D position) {
        moveTo(position.sum(new VectorImpl(-centerPos.getX(), -centerPos.getY())));
    }

    @Override
    public void setArea(int height, int widht) {
        this.areaHeight = Optional.of(height);
        this.areaWidth = Optional.of(widht);
        this.areaBorderY1 = this.centerPos.getY() - this.areaHeight.get() / 2;
        this.areaBorderY2 = this.centerPos.getY() + this.areaHeight.get() / 2;
        this.areaBorderX1 = this.centerPos.getX() - this.areaWidth.get() / 2;
        this.areaBorderX2 = this.centerPos.getX() + this.areaWidth.get() / 2;
    }

    @Override
    public void keepInArea(Position2D position) {
        var screenPos = getVirtualFromWorld(position);
        if (areaWidth.isEmpty()) {
            centerOn(position);
        } else {
            Vect movingVect = new VectorImpl(0,0);
            if (screenPos.getY() < this.areaBorderY1) {
                movingVect = movingVect.sum(new VectorImpl(0, screenPos.getY() - this.areaBorderY1));
            }
            if (screenPos.getY() > this.areaBorderY2) {
                movingVect = movingVect.sum(new VectorImpl(0, screenPos.getY() - this.areaBorderY2));
            }
            if (screenPos.getX() < this.areaBorderX1) {
                movingVect = movingVect.sum(new VectorImpl(screenPos.getX() - this.areaBorderX1, 0));
            }
            if (screenPos.getX() > this.areaBorderX2) {
                movingVect = movingVect.sum(new VectorImpl(screenPos.getX() - this.areaBorderX2, 0));
            }
            moveTo(this.cameraPos.sum(movingVect));
            //System.out.println(movingVect);
            //System.out.println(screenPos);
            //System.out.println(areaBorderX1 + "," + areaBorderX2 + "," + areaBorderY1 + "," + areaBorderY2);
        }
    }

}

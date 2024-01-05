package oop23.gfight.view;

import oop23.gfight.common.Position2D;
import oop23.gfight.common.Rotation2D;

public class ShapeGraphicsComponent extends AbstractGraphicsComponent {

    enum ShapeType {
        Circle, Rectangle 
    }

    private ShapeType shapeType;
    private int width;
    private int height;

    ShapeGraphicsComponent(EngineColor color, Position2D pos, Rotation2D rot, ShapeType shapeType, int width, int height) {
        super(color, pos, rot);
        this.shapeType = shapeType;
        this.width = width;
        this.height = height;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    
}

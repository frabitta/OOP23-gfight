package gfight.view;

import gfight.common.Position2D;
import gfight.common.Rotation2D;

/**
 * A graphics component to represent geometric shapes.
 */
public class ShapeGraphicsComponent extends AbstractGraphicsComponent {

    /**
     * available shapes.
     */
    enum ShapeType {
        CIRCLE, RECTANGLE 
    }

    private ShapeType shapeType;
    private int width;
    private int height;

    ShapeGraphicsComponent(final EngineColor color, final Position2D pos, final Rotation2D rot,
    final ShapeType shapeType, final int width, final int height) {
        super(color, pos, rot);
        this.shapeType = shapeType;
        this.width = width;
        this.height = height;
    }

    /**
     * @return shapeType of the component.
     */
    public ShapeType getShapeType() {
        return shapeType;
    }

    /**
     * A setter of the shape type.
     * @param shapeType
     */
    public void setShapeType(final ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    /**
     * @return width of the component.
     */
    public int getWidth() {
        return width;
    }

    /**
     * A setter of the width of the component.
     * @param width
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * @return height of the component.
     */
    public int getHeight() {
        return height;
    }

    /**
     * A setter of the height of the component.
     * @param height
     */
    public void setHeight(final int height) {
        this.height = height;
    }

}

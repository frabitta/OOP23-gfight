package gfight.engine.graphics.api;

import java.util.List;

import gfight.common.api.Position2D;

/**
 * A component to describe how an object should be visualized.
 */
public interface GraphicsComponent {

    /**
     * Colors available in the game.
     * (we don't use JSwing colors to allow different implementations of the view)
     */
    enum EngineColor {
        /**
         * Blue.
         */
        BLUE,
        /**
         * Red.
         */
        RED, 
        /**
         * Black.
         */
        BLACK,
        /**
         * Yellow.
         */
        YELLOW
    }

    /**
     * Type of graphic component.
     */
    enum GraphicType {
        /**
         * The component is part of the world.
         */
        WORLD,
        /**
         * The component is part of the HUD.
         */
        HUD
    }

    /**
     * @return the color of the object.
     */
    EngineColor getColor();

    /**
     * @return the position in which to print the object.
     */
    List<Position2D> getPositions();

    /**
     * A setter for the color of the object.
     * @param color
     */
    void setColor(EngineColor color);

    /**
     * A setter for the position of the object.
     * @param pos
     */
    void setPositions(List<Position2D> pos);

    /**
     * Returns the type of graphics component.
     * @return type of the GraphicsComponent
     */
    GraphicType getType();

}

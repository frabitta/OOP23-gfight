package gfight.engine.graphics.api;

import gfight.common.Position2D;
import java.util.List;

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

}

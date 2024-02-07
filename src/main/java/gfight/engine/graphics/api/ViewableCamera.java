package gfight.engine.graphics.api;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent.GraphicType;

/**
 * Interface of a Camera that gives world points in relation to its position.
 */
public interface ViewableCamera {

    /**
     * Given a World-related/Virtual position it returns the corrisponding Screen-related position. 
     * @param pos
     * @return screen-related position.
     */
    Position2D getScreenPosition(Position2D pos, GraphicType type);

    /**
     * Given a World-related/Virtual position it returns the corrisponding Screen-related position. 
     * @param pos   List of position
     * @return list of screen-related positions.
     */
    List<Position2D> getScreenPositions(List<Position2D> pos, GraphicType type);

    /**
     * Given a Screen-related position it returns the corrisponding World-related position. 
     * @param pos
     * @return world-related position.
     */
    Position2D getWorldPosition(Position2D pos);

    /**
     * Gives the camera the dimensions of the window.
     * @param width lenght of the window in pixels
     * @param height height of the window in pixels
     */
    void setScreenDimension(double width, double height);

    /**
     * Returns the horizontal offset of the image.
     * @return double of pixels offest
     */
    double getHoriOffset();

    /**
     * Returns the vertical offset of the image.
     * @return double of pixels offest
     */
    double getVertOffset();

    /**
     * Returns the ratio of resizing of the components.
     * @return double of ratio.
     */
    double getSizeRatio();

}

package gfight.engine.graphics.api;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent.GraphicType;

/**
 * Interface of a Camera that gives world points in relation to its position.
 */
public interface ViewCamera {

    /**
     * Given a World-related/Virtual position it returns the corrisponding Screen-related position. 
     * @param pos   world-related position if of type WORLD, virtual position if of type HUD
     * @param type  GraphicType of the component
     * @return screen-related position.
     */
    Position2D getScreenPosition(Position2D pos, GraphicType type);

    /**
     * Given a World-related/Virtual position it returns the corrisponding Screen-related position. 
     * @param pos   List of positions: world-related position if of type WORLD, virtual position if of type HUD
     * @param type  GraphicType of the component
     * @return list of screen-related positions.
     */
    List<Position2D> getScreenPositions(List<Position2D> pos, GraphicType type);

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

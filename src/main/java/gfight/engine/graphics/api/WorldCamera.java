package gfight.engine.graphics.api;

import gfight.common.api.Position2D;

/**
 * Interface of a camera that's able to move in space.
 */
public interface WorldCamera {

    /**
     * Moves the camera to the new position.
     * @param position world position
     */
    void moveTo(Position2D position);

    /**
     * Given a Screen-related position it returns the corrisponding World-related position. 
     * @param pos screen-related position
     * @return world-related position.
     */
    Position2D getWorldPosition(Position2D pos);

    /**
     * Keep the position given in the center of the screen.
     * @param position world position to center
     */
    void centerOn(Position2D position);

    /**
     * Sets the area to be used in the keepInArea method.
     * @param height height of the rectangle
     * @param width widht of the rectangle
     */
    void setArea(int height, int width);

    /**
     * Keeps the world position in the area defined by setArea.
     * @param position world position to keep in the area
     */
    void keepInArea(Position2D position);

}

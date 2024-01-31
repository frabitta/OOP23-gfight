package gfight.engine.graphics.api;

import gfight.common.Position2D;

/**
 * Interface of a Camera that gives world points in relation to its position.
 */
public interface ViewableCamera {

    /**
     * 
     * @param pos
     * @return position relative to the camera.
     */
    Position2D getRelativePosition(Position2D pos);

}

package gfight.engine.graphics.api;

import gfight.common.api.Position2D;

/**
 * Interface of a Camera that gives world points in relation to its position.
 */
public interface ViewableCamera {

    /**
     * Given a World-related position it returns the corrisponding Screen-related position. 
     * @param pos
     * @return screen-related position.
     */
    Position2D getScreenPosition(Position2D pos);

    /**
     * Given a Screen-related position it returns the corrisponding World-related position. 
     * @param pos
     * @return world-related position.
     */
    Position2D getWorldPosition(Position2D pos);

}

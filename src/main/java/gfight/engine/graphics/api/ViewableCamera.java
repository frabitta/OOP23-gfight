package gfight.engine.graphics.api;

import gfight.common.Position2D;

public interface ViewableCamera {
    
    Position2D getRelativePosition(Position2D pos);

}

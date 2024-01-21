package gfight.world.api;

import org.locationtech.jts.geom.Polygon;

public interface GameEntity {

    /**
     * 
     * @return the boudingBox of the game entity
     */
    Polygon getHitBox();
}

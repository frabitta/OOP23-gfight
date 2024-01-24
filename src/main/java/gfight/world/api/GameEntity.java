package gfight.world.api;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

public interface GameEntity {

    /**
     * 
     * @return the boudingBox of the game entity
     */
    Polygon getHitBox();

    /**
     * set the position of the center of the Object
     * @param position you want the object to be
     */
    void setPosition(Coordinate position);

    /**
     * return where is the object
     */
    Coordinate getPosition();
}

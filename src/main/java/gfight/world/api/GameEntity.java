package gfight.world.api;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.Set;

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

    /**
     * 
     * @param gameObjects are all the objects to be tested
     * @return all the objects in collision with this
     */
    Set<GameEntity> getAllCollided(Set<GameEntity> gameObjects);
}

package gfight.world.api;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import gfight.engine.graphics.api.GraphicsComponent;

import java.util.Set;

/**
 * An interface for any entity in the game.
 */
public interface GameEntity {

    /**
     * 
     * @return the boudingBox of the game entity
     */
    Polygon getHitBox();

    /**
     * Set the position of the center of the Object.
     * 
     * @param position you want the object to be
     */
    void setPosition(Coordinate position);

    /**
     * Return where is the object.
     * @return object position
     */
    Coordinate getPosition();

    /**
     * 
     * @param gameObjects are all the objects to be tested
     * @return all the objects in collision with this
     */
    Set<GameEntity> getAllCollided(Set<GameEntity> gameObjects);

    /**
     * Set ignoredEntities for the related entity.
     * 
     * @param ignoredEntities
     */
    void setIgnoredEntities(Set<GameEntity> ignoredEntities);

    /**
     * 
     * @return the graphic component of the Entity
     */
    GraphicsComponent getGraphics();
}

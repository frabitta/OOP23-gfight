package gfight.world.entity.api;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.hitbox.api.Hitbox;

import java.util.Set;
import java.util.List;

/**
 * An interface for any entity in the game.
 */
public interface GameEntity {

    /**
     * 
     * @return the boudingBox of the game entity
     */
    Hitbox getHitBox();

    /**
     * Set the position of the center of the Object.
     * 
     * @param position you want the object to be
     */
    void setPosition(Position2D position);

    /**
     * Return where is the object.
     * @return object position
     */
    Position2D getPosition();

    /**
     * 
     * @param gameObjects are all the objects to be tested
     * @return all the objects in collision with this
     */
    Set<GameEntity> getAllCollided(Set<? extends GameEntity> gameObjects);

    /**
     * Set ignoredEntities for the related entity.
     * 
     * @param ignoredEntities
     */
    void setIgnoredEntities(Set<GameEntity> ignoredEntities);

    /**
     * 
     * @return the graphic components of the Entity
     */
    Set<GraphicsComponent> getGraphics();

    /**
     * 
     * @return the coordinates of the entity
     */
    List<Position2D> getPosition2Ds();

    /**
     * Set the coordinates of the entity.
     * @param vertexes
     */
    void setCoordinates(List<Position2D> vertexes);

    void setGraphics(Set<GraphicsComponent> graphics);
}

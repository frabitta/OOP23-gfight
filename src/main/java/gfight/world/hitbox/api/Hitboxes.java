package gfight.world.hitbox.api;

import gfight.common.Position2D;
import gfight.common.api.Hitbox;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.GameEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An interface that can be used to perform operations on Hitboxes.
 */
public interface Hitboxes {

    /**
     * A class that calulates if a Polygon is colliding with another object
     * with hitbox.
     * 
     * @param collider  is the object that can have a change in his behaviour
     *                  or cause that after a collision
     * @param coollided is the object that can be hitted
     * @return if the collision happens
     */
    boolean isColliding(Hitbox collider, Hitbox coollided);

    /**
     * It rotates a list of vertex.
     * 
     * @param theta   angle of rotation in radiants
     * @param polygon original list of vertexes
     * @return new modified list
     */
    List<Position2D> rotate(List<Position2D> polygon, double theta);

    /**
     * it signals to all onjects that hitboxes needs to be recalculated.
     * 
     * @param gameobjects all object you want to calculate updated hitboxes
     */
    void freeHitboxes(Set<CachedGameEntity> gameobjects);

    /**
     * it crreates a graph of all the collision of gameobjects.
     * 
     * @param gameObjects you want to calculate collision
     * @return Graph of the colisions
     */
    Map<GameEntity, Set<GameEntity>> getAllCollision(Set<GameEntity> gameObjects);
}

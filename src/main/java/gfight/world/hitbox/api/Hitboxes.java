package gfight.world.hitbox.api;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import java.util.List;

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
     * Returns the rotated list of vertex.
     * 
     * @param theta   angle of rotation in radiants
     * @param polygon original list of vertexes
     * @param center  the center of rotation
     * @return new modified list
     */
    List<Position2D> rotate(List<Position2D> polygon, double theta, Position2D center);

    /**
     * Returns the rotated list of vertexes in order to point in the target.
     * 
     * @param polygon     original list of vertexes.
     * @param pointingDir original pointing direction of the polygon.
     * @param center      center of the polygon.
     * @param target      target of the rotation.
     * @return the rotated list.
     */
    List<Position2D> rotateTo(List<Position2D> polygon, Vect pointingDir, Position2D center, Position2D target);
}

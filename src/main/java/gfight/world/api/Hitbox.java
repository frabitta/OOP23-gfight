package gfight.world.api;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.List;

public interface Hitbox {
     /**
     * Creates the polygon of the hitbox of the elements in the game
     * 
     * @param vertexes of the polygon
     * @return polygon geometry itself
     */
    public Polygon getGeometry(List<Coordinate> vertexes);

    /**
     * A class that calulates if a Polygon is colliding with another object
     * with hitbox
     * 
     * @param collider  is the object that can have a change in his behaviour
     *                  or cause that after a collision
     * @param coollided is the object that can be hitted
     * @return if the collision happens
     */
    public boolean isColliding(Polygon collider, Polygon coollided);

    /**
     * Crea un poligono rappresentante un quadrato dato il centro e la lunghezza del
     * lato.
     * 
     * @param centre centro del quadrato
     * @param side   lunghezza del lato
     * @return poligono rappresentante il quadrato
     */
    public Polygon getSquare(Coordinate centre, double side);
}

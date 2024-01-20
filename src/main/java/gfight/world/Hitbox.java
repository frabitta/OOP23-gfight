package gfight.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.prep.PreparedPolygon;

public class Hitbox {

    /**
     * Creates the polygon of the hitbox of the elements in the game
     * 
     * @param vertexes of the polygon
     * @return polygon geometry itself
     */
    public Polygon getGeometry(List<Coordinate> vertexes) {
        final GeometryFactory factory = new GeometryFactory();
        final List<Coordinate> polygon = new ArrayList<>(vertexes);
        polygon.add(polygon.get(0));
        return factory.createPolygon(polygon.toArray(new Coordinate[0]));
    }

    /**
     * A class that calulates if a Polygon is colliding with another object
     * with hitbox
     * 
     * @param collider  is the object that can have a change in his behaviour
     *                  or cause that after a collision
     * @param coollided is the object that can be hitted
     * @return if the collision happens
     */
    public boolean isColliding(Polygon collider, Polygon coollided) {
        final PreparedPolygon myObject = new PreparedPolygon(collider);
        return myObject.intersects(coollided);
    }
}

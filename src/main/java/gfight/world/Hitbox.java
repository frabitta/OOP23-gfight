package gfight.world;

import java.util.Arrays;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.prep.PreparedPolygon;

public class Hitbox {

    /**
     * Creates the polygon of the hitbox of the elements in the game
     * @param vertexes of the polygon
     * @return polygon geometry itself
     */
    public Polygon getGeometry(Coordinate[] vertexes){
        final GeometryFactory factory = new GeometryFactory();
        final Coordinate[] polygon = Arrays.copyOf(vertexes, vertexes.length+1);
        polygon[vertexes.length]=polygon[0];
        return factory.createPolygon(polygon);
    }


    /**
     * A class that calulates if a Polygon is colliding with another object 
     * with hitbox
     * @param collider is the object that can have a change in his behaviour 
     * or cause that after a collision
     * @param coollided is the object that can be hitted
     * @return if the collision happens
     */
    public boolean isColliding(Polygon collider, Polygon coollided){
        final PreparedPolygon myObject = new PreparedPolygon(collider);
        return myObject.intersects(coollided);
    }
}

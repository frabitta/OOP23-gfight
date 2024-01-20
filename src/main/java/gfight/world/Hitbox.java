package gfight.world;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.prep.PreparedPolygon;

public class Hitbox {

    /**
     * Creates the polygon of the hitbox of the elements in the game
     * @param vertexes of the polygon
     * @return polygon geometry itself
     */
    public Geometry getGeometry(Coordinate[] vertexes){
        final GeometryFactory factory = new GeometryFactory();
        return factory.createPolygon(vertexes);
    }


    /**
     * A class that calulates if a Polygon is colliding with another object 
     * with hitbox
     * @param collider is the object that can have a change in his behaviour 
     * or cause that after a collision
     * @param coollided is the object that can be hitted
     * @return if the collision happens
     */
    boolean isColliding(Polygon collider, Geometry coollided){
        PreparedPolygon myObject = new PreparedPolygon(collider);
        return myObject.crosses(coollided);
    }
}

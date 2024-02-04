package gfight.world.hitbox.api;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

/**
 * an interface to create hiboxes of entities and perform operation on them.
 */
public interface Hitbox {
    /**
     * 
     * @return the list of vertexes that composes the hitbox.
     */
    List<Coordinate> getVertexes();

    /**
     * 
     * @return a polygon representig Hitbox
     */
    Polygon getPolygonalHitbox();
}

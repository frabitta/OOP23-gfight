package gfight.world.hitbox.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

import gfight.common.api.Position2D;
import gfight.world.hitbox.api.Hitbox;

/**
 * A concrete implementation of the Hitbox interface representing a polygonal
 * hitbox in a game world.
 */
public final class HitboxImpl implements Hitbox {

    final private Polygon hitbox;

    /**
     * Constructs a HitboxImpl instance with the specified vertex positions.
     * 
     * @param vertexes A list of positions defining the vertices of the hitbox
     *                 polygon.
     */
    public HitboxImpl(final List<Position2D> vertexes) {
        final GeometryFactory factory = new GeometryFactory();
        final List<Position2D> polygon = new ArrayList<>(vertexes);
        polygon.add(polygon.get(0));
        hitbox = factory.createPolygon(polygon.toArray(new Coordinate[0]));
    }

    public Polygon getPolygonalHitbox() {
        return hitbox;
    }

    @Override
    public List<Coordinate> getVertexes() {
        return Arrays.asList(hitbox.getCoordinates());
    }

}

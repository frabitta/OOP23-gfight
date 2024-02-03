package gfight.common.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import gfight.common.Position2D;
import gfight.common.api.Hitbox;

public class HitboxImpl implements Hitbox {

    final Polygon hitbox;

    public HitboxImpl(List<Position2D> vertexes){
        final GeometryFactory factory = new GeometryFactory();
        final List<Position2D> polygon = new ArrayList<>(vertexes);
        polygon.add(polygon.get(0));
        hitbox = factory.createPolygon(polygon.toArray(new Coordinate[0]));
    }

    public Polygon getPolygonalHitbox(){
        return hitbox;
    }

    @Override
    public List<Coordinate> getVertexes() {
        return Arrays.asList(hitbox.getCoordinates());
    }
    
}

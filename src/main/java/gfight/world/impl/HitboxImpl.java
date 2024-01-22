package gfight.world.impl;

import java.util.ArrayList;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.prep.PreparedPolygon;

import gfight.world.api.Hitbox;

public class HitboxImpl implements Hitbox {

    public Polygon getGeometry(List<Coordinate> vertexes) {
        final GeometryFactory factory = new GeometryFactory();
        final List<Coordinate> polygon = new ArrayList<>(vertexes);
        polygon.add(polygon.get(0));
        return factory.createPolygon(polygon.toArray(new Coordinate[0]));
    }

    public boolean isColliding(Polygon collider, Polygon coollided) {
        final PreparedPolygon myObject = new PreparedPolygon(collider);
        return myObject.intersects(coollided);
    }

    public Polygon getSquare(Coordinate centre, double side) {
        double halfSide = side / 2.0;
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(centre.getX() - halfSide, centre.getY() - halfSide));
        coordinates.add(new Coordinate(centre.getX() + halfSide, centre.getY() - halfSide));
        coordinates.add(new Coordinate(centre.getX() + halfSide, centre.getY() + halfSide));
        coordinates.add(new Coordinate(centre.getX() - halfSide, centre.getY() + halfSide));
        return getGeometry(coordinates);
    }
}

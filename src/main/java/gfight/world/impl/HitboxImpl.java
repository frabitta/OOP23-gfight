package gfight.world.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.prep.PreparedPolygon;
import org.locationtech.jts.geom.util.AffineTransformation;

import gfight.world.api.Hitbox;

/**
 * An implementation of Hitbox Interface.
 */
public final class HitboxImpl implements Hitbox {

    @Override
    public Polygon getGeometry(final List<Coordinate> vertexes) {
        final GeometryFactory factory = new GeometryFactory();
        final List<Coordinate> polygon = new ArrayList<>(vertexes);
        polygon.add(polygon.get(0));
        return factory.createPolygon(polygon.toArray(new Coordinate[0]));
    }

    @Override
    public boolean isColliding(final Polygon collider, final Polygon coollided) {
        final PreparedPolygon myObject = new PreparedPolygon(collider);
        return myObject.intersects(coollided);
    }

    @Override
    public Polygon getSquare(final Coordinate centre, final double side) {
        double halfSide = side / 2.0;
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(centre.getX() - halfSide, centre.getY() - halfSide));
        coordinates.add(new Coordinate(centre.getX() + halfSide, centre.getY() - halfSide));
        coordinates.add(new Coordinate(centre.getX() + halfSide, centre.getY() + halfSide));
        coordinates.add(new Coordinate(centre.getX() - halfSide, centre.getY() + halfSide));
        return getGeometry(coordinates);
    }

    @Override
    public List<Coordinate> rotate(final List<Coordinate> polygon, final double theta) {
        Polygon poligon = getGeometry(polygon);
        AffineTransformation rotation = AffineTransformation.rotationInstance(theta);
        List<Coordinate> rotatedPolygon = Arrays.asList(rotation.transform(poligon).getCoordinates());
        if (!rotatedPolygon.isEmpty()) {
            return new ArrayList<>(rotatedPolygon.subList(0, rotatedPolygon.size() - 1));
        } else {
            return new ArrayList<>();
        }
    }
}

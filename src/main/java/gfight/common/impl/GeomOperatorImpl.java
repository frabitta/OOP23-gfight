package gfight.common.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.locationtech.jts.geom.Coordinate;

import gfight.common.api.GeomOperator;

public class GeomOperatorImpl implements GeomOperator {
    public Coordinate sum(Coordinate start, Vector2D vector) {
        return new Coordinate(start.x + vector.getX(), start.y + vector.getY());
    }

    public Vector2D distance(Coordinate point1, Coordinate point2) {
        return new Vector2D(point1.x - point2.x, point1.y - point2.y);
    }
}

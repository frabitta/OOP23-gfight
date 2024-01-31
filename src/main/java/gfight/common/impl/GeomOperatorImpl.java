package gfight.common.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.locationtech.jts.geom.Coordinate;

import gfight.common.api.GeomOperator;

/**
 * Implementation of the GeomOperator Interface.
 */
public final class GeomOperatorImpl implements GeomOperator {
    @Override
    public Coordinate sum(final Coordinate start, final Vector2D vector) {
        return new Coordinate(start.x + vector.getX(), start.y + vector.getY());
    }

    @Override
    public Vector2D distance(final Coordinate point1, final Coordinate point2) {
        return new Vector2D(point1.x - point2.x, point1.y - point2.y);
    }
}

package gfight.common.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import gfight.common.Position2D;
import gfight.common.api.GeomOperator;

/**
 * Implementation of the GeomOperator Interface.
 */
public final class GeomOperatorImpl implements GeomOperator {
    @Override
    public Position2D sum(final Position2D start, final Vector2D vector) {
        return new Position2DImpl(start.getX() + vector.getX(), start.getY() + vector.getY());
    }

    @Override
    public Vector2D distance(final Position2D point1, final Position2D point2) {
        return new Vector2D(point1.getX() - point2.getX(), point1.getY() - point2.getY());
    }
}

package gfight.common.impl;

import org.locationtech.jts.geom.Coordinate;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;

/**
 * Implementation of Position2D.
 */
public final class Position2DImpl extends Coordinate implements Position2D {

    /**
     * Constructs a new Position2DImpl with the specified x and y coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Position2DImpl(final double x, final double y) {
        super(x, y);
    }

    /**
     * Constructs a new Position2DImpl.
     *
     * @param pos The Position2D to copy coordinates from.
     */
    public Position2DImpl(final Position2D pos) {
        super(pos.getX(), pos.getY());
    }

    @Override
    public Position2D sum(final Vect a) {
        return new Position2DImpl(getX() + a.getX(), getY() + a.getY());
    }

    @Override
    public double getDistance(Position2D point) {
        if (point instanceof Position2DImpl) {
            return super.distance((Coordinate) point);
        } else {
            throw new IllegalArgumentException("Incompatible type for sum operation");
        }
    }
}

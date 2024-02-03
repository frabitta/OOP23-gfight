package gfight.common.impl;

import org.locationtech.jts.geom.Coordinate;

import gfight.common.Position2D;

public class Position2DImpl extends Coordinate implements Position2D {
    public Position2DImpl(double x, double y) {
        super(x, y);
    }

    public Position2DImpl(Position2D pos) {
        super(pos.getX(), pos.getY());
    }
}

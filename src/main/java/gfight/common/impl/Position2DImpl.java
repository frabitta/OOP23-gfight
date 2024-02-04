package gfight.common.impl;

import org.locationtech.jts.geom.Coordinate;

import gfight.common.Position2D;
import gfight.common.api.Vect;

public class Position2DImpl extends Coordinate implements Position2D {
    public Position2DImpl(double x, double y) {
        super(x, y);
    }

    public Position2DImpl(Position2D pos) {
        super(pos.getX(), pos.getY());
    }

    @Override
    public Position2D sum(Vect a) {
        return new Position2DImpl(getX() + a.getX(), getY() + a.getY());
    }

}

package gfight.common.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.common.Position2D;
import gfight.common.api.Vect;

public class VectorImpl extends Vector2D implements Vect {

    public VectorImpl(double x, double y) {
        super(x, y);
    }

    public VectorImpl(double a, Vect vect) {
        super(a, (Vector2D) vect);
    }

    public VectorImpl(Position2D a, Position2D b) {
        super(a.getX() - b.getX(), a.getY() - b.getY());
    }

    @Override
    public Vect add(Vect vector) {
        return (Vect) add(vector);
    }

    @Override
    public Vect scale(double value) {
        return (Vect) scalarMultiply(value);
    }

    @Override
    public Vect revert() {
        return (Vect) negate();
    }

    @Override
    public Vect norm() {
        return (Vect) normalize();
    }

}

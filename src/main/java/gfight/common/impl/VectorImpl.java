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

    public VectorImpl(Vector2D vector) {
        super(vector.getX(), vector.getY());
    }

    @Override
    public Vect sum(Vect vector) {
        if (vector instanceof VectorImpl) {
            return new VectorImpl(add((Vector2D) vector));
        } else {
            throw new IllegalArgumentException("Incompatible type for sum operation");
        }
    }

    @Override
    public Vect scale(double value) {
        return new VectorImpl(scalarMultiply(value));
    }

    @Override
    public Vect revert() {
        return new VectorImpl(negate());
    }

    @Override
    public Vect norm() {
        return new VectorImpl(normalize());
    }

}

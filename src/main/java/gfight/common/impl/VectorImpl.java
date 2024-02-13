package gfight.common.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;

/**
 * Implementation of the interface.
 */
public final class VectorImpl extends Vector2D implements Vect {

    /** Serializable UID. */
    private static final long serialVersionUID = 266938651998679754L;

    /**
     * Constructs a new VectorImpl with the specified x and y components.
     *
     * @param x The x-component.
     * @param y The y-component.
     */
    public VectorImpl(final double x, final double y) {
        super(x, y);
    }

    /**
     * Constructs a new VectorImpl by scaling the vector.
     *
     * @param a    The scalar by which to scale the vector.
     * @param vect The vector to be scaled.
     */
    public VectorImpl(final double a, final Vect vect) {
        super(a * vect.getX(), a * vect.getY());
    }

    /**
     * Constructs a new VectorImpl representing the vector from position {@code b}
     * to position {@code a}.
     *
     * @param a The start position.
     * @param b The end position.
     */
    public VectorImpl(final Position2D a, final Position2D b) {
        super(b.getX() - a.getX(), b.getY() - a.getY());
    }

    /**
     * Constructs a new VectorImpl by copying the coordinates from the provided
     * vector.
     *
     * @param vector The Vector2D to copy coordinates from.
     */
    public VectorImpl(final Vector2D vector) {
        super(vector.getX(), vector.getY());
    }

    @Override
    public Vect sum(final Vect vector) {
        if (vector instanceof VectorImpl) {
            return new VectorImpl(add((Vector2D) vector));
        } else {
            throw new IllegalArgumentException("Incompatible type for sum operation");
        }
    }

    @Override
    public Vect scale(final double value) {
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

    @Override
    public double dotProduct(final Vect a) {
        if (a instanceof VectorImpl) {
            return this.dotProduct((Vector2D) a);
        } else {
            throw new IllegalArgumentException("Incompatible type for dotProduct operation");
        }
    }

    @Override
    public double anglecalc(final Vect v1) {
        if ((v1.getX() != 0 || v1.getY() != 0) && (this.getX() != 0 || this.getY() != 0)) {
            if (v1 instanceof VectorImpl) {
                return Vector2D.angle(this, (VectorImpl) v1);
            } else {
                throw new IllegalArgumentException("Incompatible type for angle calculation");
            }
        }
        return 0;
    }

}

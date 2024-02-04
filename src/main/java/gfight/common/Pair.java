package gfight.common;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;

/**
 * Pair class of two integers.
 */
public class Pair implements Position2D {

    private final double x;
    private final double y;

    /**
     * Constructor of the pair object.
     * @param x
     * @param y
     */
    public Pair(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final double getX() {
        return x;
    }

    @Override
    public final double getY() {
        return y;
    }

    @Override
    public Position2D sum(Vect a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sum'");
    }

}

package gfight.common;

/**
 * Pair class of two integers.
 */
public class Pair implements Position2D {

    private final int x;
    private final int y;

    /**
     * Constructor of the pair object.
     * @param x
     * @param y
     */
    public Pair(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final int getX() {
        return x;
    }

    @Override
    public final int getY() {
        return y;
    }

}

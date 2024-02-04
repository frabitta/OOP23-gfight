package gfight.common.api;

/**
 * Interface to store 2D positions.
 */
public interface Position2D {

    /**
     * @return x
     */
    double getX();

    /**
     * @return y
     */
    double getY();

    /**
     * Adds the vector to the current position and returns a new
     * Position2DImpl representing the result.
     *
     * @param a The vector to add.
     * @return A new Position2DImpl representing the sum of the current position and
     *         vector.
     */
    Position2D sum(Vect a);
}

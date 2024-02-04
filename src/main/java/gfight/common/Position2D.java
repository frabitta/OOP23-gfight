package gfight.common;

import gfight.common.api.Vect;

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

    Position2D sum(Vect a);
}

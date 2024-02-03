package gfight.common.api;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import gfight.common.Position2D;

/**
 * A utility class for Operation between Vector and Coordinate.
 */
public interface GeomOperator {
    /**
     * it sums a vector to a pair of coordinates.
     * @param start initial position
     * @param vector to be summed
     * @return Coordinate of the point obtained with the sum
     */
    Position2D sum(Position2D start, Vector2D vector);

    /**
     * Computes the distance beetween 2 points (p1 - p2).
     * @param point1 
     * @param point2
     * @return Vector distance
     */
    Vector2D distance(Position2D point1, Position2D point2);
}

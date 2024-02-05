package gfight.world.entity.api;

import java.util.List;

import gfight.common.api.Position2D;

/**
 * An interface with methods to calculate the vertexes of different shapes.
 */
public interface VertexCalculator {
    /**
     * Calculate vertexes of an equilater triangle.
     * 
     * @param sideLength of the triangle
     * @param position   the centre of the triangle
     * @return a list with the vertexes of the triangle
     */
    List<Position2D> triangle(double sideLength, Position2D position);

    /**
     * Calculate vertexes of a square.
     * 
     * @param sideLength of the square
     * @param position   the centre of the square
     * @return a list with the vertexes of the square
     */
    List<Position2D> square(double sideLength, Position2D position);

}

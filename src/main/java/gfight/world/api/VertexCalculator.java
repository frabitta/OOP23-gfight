package gfight.world.api;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

public interface VertexCalculator {
    /**
     * Calculate vertexes of an equilater triangle
     * 
     * @param sideLength of the triangle
     * @param position   the centre of the triangle
     * @return a list with the vertexes of the triangle
     */
    List<Coordinate> triangle(double sideLength, Coordinate position);

    /**
     * Calculate vertexes of a square
     * 
     * @param sideLength of the square
     * @param position   the centre of the square
     * @return a list with the vertexes of the square
     */
    List<Coordinate> square(double sideLength, Coordinate position);

}

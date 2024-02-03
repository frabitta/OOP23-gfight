package gfight.world.api;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

public interface VertexCalculator {
    /**
     * Calculate vertexes of an equilater triangle 
     * @param sideLength
     * @param position
     * @return a list with the vertexes of the triangle
     */
    List<Coordinate> triangle(double sideLength, Coordinate position);

    /**
     * Calculate vertexes of a square
     * @param sideLength
     * @param position
     * @return a list with the vertexes of the square
     */
    List<Coordinate> square(double sideLength, Coordinate position);

    /**
     * Calculate vertexes of a rectangle
     * @param sideLength
     * @param position
     * @return a list with the vertexes of the rectangle
     */
    List<Coordinate> rectangle(double longSideLength, double shortSideLenght, Coordinate position);
    
}

package gfight.world.impl;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

import gfight.world.api.VertexCalculator;

/**
 * This class contais the methods to calculate the vertexes of different shapes
 */
public class VertexCalculatorImpl implements VertexCalculator {

    @Override
    public List<Coordinate> triangle(final double sideLength, final Coordinate position) {
        double factor = (sideLength * Math.sqrt(3)) / 2;
        Coordinate firstPoint = new Coordinate(position.x + sideLength / 2, position.y + factor);
        Coordinate secondPoint = new Coordinate(position.x - sideLength / 2, position.y + factor);
        Coordinate thirdPoint = new Coordinate(position.x, position.y - factor);
        return List.of(firstPoint, secondPoint, thirdPoint);
    }

    @Override
    public List<Coordinate> square(final double sideLength, final Coordinate position) {
        double halfSide = sideLength / 2;
        Coordinate bottomLeft = new Coordinate(position.x - halfSide, position.y - halfSide);
        Coordinate topLeft = new Coordinate(position.x - halfSide, position.y + halfSide);
        Coordinate bottomRight = new Coordinate(position.x + halfSide, position.y - halfSide);
        Coordinate topRight = new Coordinate(position.x + halfSide, position.y + halfSide);
        return List.of(bottomLeft, bottomRight, topLeft, topRight);
    }

    @Override
    public List<Coordinate> rectangle(double longSideLength, double shortSideLenght, Coordinate position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rectangle'");
    }

}

package gfight.world.impl;

import java.util.List;

import gfight.common.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.world.api.VertexCalculator;

/**
 * This class contais the methods to calculate the vertexes of different shapes.
 */
public class VertexCalculatorImpl implements VertexCalculator {

    @Override
    public final List<Position2D> triangle(final double sideLength, final Position2D position) {
        double factor = (sideLength * Math.sqrt(3)) / 2;
        Position2D firstPoint = new Position2DImpl(position.getX() + sideLength / 2, position.getY() + factor);
        Position2D secondPoint = new Position2DImpl(position.getX() - sideLength / 2, position.getY() + factor);
        Position2D thirdPoint = new Position2DImpl(position.getX(), position.getY() - factor);
        return List.of(firstPoint, secondPoint, thirdPoint);
    }

    @Override
    public final List<Position2D> square(final double sideLength, final Position2D position) {
        double halfSide = sideLength / 2;
        Position2D bottomLeft = new Position2DImpl(position.getX() - halfSide, position.getY() - halfSide);
        Position2D topLeft = new Position2DImpl(position.getX() - halfSide, position.getY() + halfSide);
        Position2D bottomRight = new Position2DImpl(position.getX() + halfSide, position.getY() - halfSide);
        Position2D topRight = new Position2DImpl(position.getX() + halfSide, position.getY() + halfSide);
        return List.of(bottomLeft, topLeft, topRight, bottomRight);
    }

}

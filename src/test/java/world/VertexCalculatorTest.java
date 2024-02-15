package world;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.world.entity.api.VertexCalculator;
import gfight.world.entity.impl.VertexCalculatorImpl;

/**
 * Class that tests the correct calculation of the vertexes of the shapes.
 */
class VertexCalculatorTest {
    private final VertexCalculator vertexCalculator = new VertexCalculatorImpl();

    // CHECKSTYLE: MagicNumber OFF
    /**
     * Test the correct calculation of the vertexes of a regular triangle.
     */
    @Test
    void testTriangle() {
        final double sideLength = 1.0;
        final Position2D position = new Position2DImpl(0, 0);

        final List<Position2D> vertexes = vertexCalculator.triangle(sideLength, position);
        assertTrue(vertexes.size() == 3);
        assertTrue(vertexes.get(0).equals(new Position2DImpl(0, -(sideLength * Math.sqrt(3)) / 3)));
        assertTrue(vertexes.get(1).equals(new Position2DImpl(sideLength / 2, (sideLength * Math.sqrt(3)) / 6)));
        assertTrue(vertexes.get(2).equals(new Position2DImpl(-sideLength / 2, (sideLength * Math.sqrt(3)) / 6)));

    }

    /**
     * Test the correct calculation of the vertexes of a square.
     */
    @Test
    void testSquare() {
        final double sideLength = 1.0;
        final Position2D position = new Position2DImpl(0, 0);
        final List<Position2D> vertexes = vertexCalculator.square(sideLength, position);

        assertTrue(vertexes.size() == 4);
        assertTrue(vertexes.get(0).equals(new Position2DImpl(-0.5, -0.5)));
        assertTrue(vertexes.get(1).equals(new Position2DImpl(-0.5, 0.5)));
        assertTrue(vertexes.get(2).equals(new Position2DImpl(0.5, 0.5)));
        assertTrue(vertexes.get(3).equals(new Position2DImpl(0.5, -0.5)));
    }

    /**
     * Test the correct calculation of the vertexes of a rectangle.
     */
    @Test
    void testRectangle() {
        final double width = 4.0;
        final double height = 2.0;
        final Position2D position = new Position2DImpl(0, 0);
        final List<Position2D> vertexes = vertexCalculator.rectangle(width, height, position);
        assertTrue(vertexes.size() == 4);
        assertTrue(vertexes.get(0).equals(new Position2DImpl(-2, -1)));
        assertTrue(vertexes.get(1).equals(new Position2DImpl(-2, 1)));
        assertTrue(vertexes.get(2).equals(new Position2DImpl(2, 1)));
        assertTrue(vertexes.get(3).equals(new Position2DImpl(2, -1)));

    }
}

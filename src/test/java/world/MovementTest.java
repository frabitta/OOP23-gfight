package world;

import org.junit.jupiter.api.Test;

import gfight.world.movement.api.InputMovement;
import gfight.world.movement.api.MovementFactory;
import gfight.world.movement.impl.LinearMovement;
import gfight.world.movement.impl.MovementFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Class that tests movement.
 */
class MovementTest {

    /**
     * Test linear Movement.
     */
    @Test
    void testLinearMovement() {
        MovementFactory movementFactory = new MovementFactoryImpl();
        LinearMovement linearMovement = movementFactory.createLinearMovement(new Vector2D(1, 0));

        assertEquals(new Vector2D(1, 0), linearMovement.getDirection(), "Initial direction should be (1, 0)");

        linearMovement.update();

        assertEquals(new Vector2D(1, 0), linearMovement.getDirection(),
                "Direction should remain the same after update");
    }

    /**
     * Test Input Movement.
     */
    @Test
    void testInputMovement() {
        MovementFactory movementFactory = new MovementFactoryImpl();
        InputMovement inputMovement = movementFactory.createInput();

        inputMovement.addDirection(InputMovement.Directions.NORD);
        inputMovement.addDirection(InputMovement.Directions.EST);
        inputMovement.update();

        assertEquals(new Vector2D(1, 1).normalize(), inputMovement.getDirection(),
                "Initial direction should be (1, 1)");

        inputMovement.update();

        assertEquals(new Vector2D(1, 1).normalize(), inputMovement.getDirection(),
                "Direction should still be (1, 1) after update");

        inputMovement.removeDirection(InputMovement.Directions.NORD);
        inputMovement.update();

        assertEquals(new Vector2D(1, 0), inputMovement.getDirection(),
                "Direction should be (1, 0) after update");
    }
}

package world;

import org.junit.jupiter.api.Test;

import gfight.common.impl.VectorImpl;
import gfight.world.movement.api.InputMovement;
import gfight.world.movement.api.MovementFactory;
import gfight.world.movement.impl.LinearMovement;
import gfight.world.movement.impl.MovementFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        LinearMovement linearMovement = movementFactory.createLinearMovement(new VectorImpl(1, 0));

        assertEquals(new VectorImpl(1, 0), linearMovement.getDirection(), "Initial direction should be (1, 0)");

        linearMovement.update();

        assertEquals(new VectorImpl(1, 0), linearMovement.getDirection(),
                "Direction should remain the same after update");
    }

    /**
     * Test Input Movement.
     */
    @Test
    void testInputMovement() {
        MovementFactory movementFactory = new MovementFactoryImpl();
        InputMovement inputMovement = movementFactory.createInput();

        inputMovement.addDirection(InputMovement.Directions.NORTH);
        inputMovement.addDirection(InputMovement.Directions.EAST);
        inputMovement.update();

        assertEquals(new VectorImpl(1, 1).norm(), inputMovement.getDirection(),
                "Initial direction should be (1, 1)");

        inputMovement.update();

        assertEquals(new VectorImpl(1, 1).norm(), inputMovement.getDirection(),
                "Direction should still be (1, 1) after update");

        inputMovement.removeDirection(InputMovement.Directions.NORTH);
        inputMovement.update();

        assertEquals(new VectorImpl(1, 0), inputMovement.getDirection(),
                "Direction should be (1, 0) after update");
    }
}

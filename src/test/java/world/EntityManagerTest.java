package world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.world.api.EntityManager;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.impl.EntityManagerImpl;
import gfight.world.movement.impl.InputMovementImpl;

/**
 * Test the EntityManager.
 */
class EntityManagerTest {

    private static final Position2D TEST_POS = new Position2DImpl(10, 10);
    private static final int TEST_STAT = 10;

    private EntityManager manager;

    /**
     * Creates a new EntityManager before every test.
     */
    @BeforeEach
    void setUp() {
        this.manager = new EntityManagerImpl(new EntityFactoryImpl());
    }

    /**
     * Verifies that newly created entities are stored inside the manager.
     */
    @Test
    void testNewEntity() {
        assertEquals(0, this.manager.getEntities().size());
        final var testPlayer = this.manager.createPlayer(TEST_STAT, TEST_POS, TEST_STAT,
                new InputMovementImpl());
        assertTrue(this.manager.getEntities().contains(testPlayer));
        final var testChest = this.manager.createChest(TEST_STAT, TEST_POS, TEST_STAT);
        assertTrue(this.manager.getEntities().contains(testChest));
        final var testObstacle = this.manager.createObstacle(TEST_STAT, TEST_POS);
        assertTrue(this.manager.getEntities().contains(testObstacle));
        final var testEnemy = this.manager.createRunner(testPlayer, TEST_STAT, TEST_POS, TEST_STAT, null);
        assertTrue(this.manager.getEntities().contains(testEnemy));
    }

    /**
     * Verifies whether there are still enemies alive.
     */
    @Test
    void testIsClear() {
        assertTrue(this.manager.isClear());
        final var testPlayer = this.manager.createPlayer(TEST_STAT, TEST_POS, TEST_STAT,
                new InputMovementImpl());
        assertTrue(this.manager.isClear());
        this.manager.createRunner(testPlayer, TEST_STAT, TEST_POS, TEST_STAT, null);
        assertFalse(this.manager.isClear());
    }

    /**
     * Verifies that dead entities are succesfully destroyed.
     */
    @Test
    void testClean() {
        final var testPlayer = this.manager.createPlayer(TEST_STAT, TEST_POS, 0, new InputMovementImpl());
        this.manager.createRunner(testPlayer, TEST_STAT, TEST_POS, TEST_STAT, null);
        this.manager.createChest(TEST_STAT, TEST_POS, 0);
        assertEquals(3, this.manager.getEntities().size());
        this.manager.clean();
        assertEquals(1, this.manager.getEntities().size());
    }
}

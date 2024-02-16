package world;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import gfight.common.impl.Position2DImpl;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.map.api.GameMap;
import gfight.world.map.impl.GameMapImpl;
import gfight.world.movement.impl.InputMovementImpl;

/**
 * Test the GameMap.
 */
class GameMapTest {

    private static final int TEST_STAT = 10;

    private final GameMap map = new GameMapImpl("map1");

    /**
     * Verifies the map has been created succesfully.
     */
    @Test
    void testCreation() {
        assertNotEquals(Set.of(), this.map.getObstaclesPositions());
        assertNotEquals(Set.of(), this.map.getObstaclesPositions());
        assertNotEquals(Map.of(), this.map.getSpawnersPositions());
        assertNotNull(this.map.getChestPosition());
        assertNotNull(this.map.getPlayerSpawn());
    }

    /**
     * Verifies that entities can't go outside the map.
     */
    @Test
    void testSearchTile() {
        final var testPlayer = new EntityFactoryImpl().createPlayer(TEST_STAT, new Position2DImpl(0, 0), TEST_STAT,
                new InputMovementImpl());
        assertDoesNotThrow(() -> this.map.searchTile(testPlayer.getPosition()));
        testPlayer.setPosition(new Position2DImpl(-1, -1));
        assertThrows(IllegalStateException.class, () -> this.map.searchTile(testPlayer.getPosition()));
        testPlayer.setPosition(new Position2DImpl(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertThrows(IllegalStateException.class, () -> this.map.searchTile(testPlayer.getPosition()));
    }
}

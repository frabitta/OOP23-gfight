package world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.common.impl.VectorImpl;
import gfight.world.entity.api.ActiveEntity;
import gfight.world.entity.api.CachedGameEntity;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.EntityFactory;
import gfight.world.entity.api.VertexCalculator;
import gfight.world.entity.api.Character.CharacterType;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.entity.impl.VertexCalculatorImpl;
import gfight.world.map.api.GameMap;
import gfight.world.map.impl.GameMapImpl;
import gfight.world.movement.impl.InputMovementImpl;
import gfight.world.weapon.api.Projectile;

/**
 * Class that tests the correct creation of entities.
 */
public class EntityFactoryTest {
    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private final VertexCalculator vertexCalculator = new VertexCalculatorImpl();

    // CHECKSTYLE: MagicNumber OFF
    /**
     * Test the correct creation of entity Player.
     */
    @Test
    public void testPlayer() {
        Position2D position = new Position2DImpl(100, 100);
        Character player = entityFactory.createPlayer(10, position, 10, new InputMovementImpl());
        assertNotNull(player);
        assertEquals(CharacterType.PLAYER, player.getType());
        assertEquals(position, player.getPosition());
        assertEquals(10, player.getHealth());
        assertEquals(vertexCalculator.triangle(10, position), player.getPosition2Ds());
        assertTrue(player.isAlive());
    }

    /**
     * Test the correct creation of entity Chest.
     */
    @Test
    public void testChest() {
        Position2D position = new Position2DImpl(100, 100);
        ActiveEntity chest = entityFactory.createChest(40, position, 10);
        assertNotNull(chest);
        assertEquals(position, chest.getPosition());
        assertEquals(10, chest.getHealth());
        assertEquals(vertexCalculator.square(40, position), chest.getPosition2Ds());
        assertTrue(chest.isAlive());
    }

    /**
     * Test the correct creation of entity Shooter (enemy).
     */
    @Test
    public void testShooter() {
        Position2D position = new Position2DImpl(100, 100);
        GameMap map = new GameMapImpl("map1");
        ActiveEntity target = entityFactory.createChest(40, new Position2DImpl(200, 200), 100);
        Character enemy = entityFactory.createShooter(target, 10, position, 10, map);
        assertNotNull(enemy);
        assertEquals(position, enemy.getPosition());
        assertEquals(CharacterType.SHOOTER, enemy.getType());
        assertEquals(10, enemy.getHealth());
        assertEquals(vertexCalculator.triangle(10, position), enemy.getPosition2Ds());
        assertTrue(enemy.isAlive());
    }

    /**
     * Test the correct creation of entity Runner (enemy).
     */
    @Test
    public void testRunner() {
        Position2D position = new Position2DImpl(100, 100);
        GameMap map = new GameMapImpl("map1");
        ActiveEntity target = entityFactory.createChest(40, new Position2DImpl(200, 200), 100);
        Character enemy = entityFactory.createRunner(target, 10, position, 10, map);
        assertNotNull(enemy);
        assertEquals(position, enemy.getPosition());
        assertEquals(CharacterType.RUNNER, enemy.getType());
        assertEquals(10, enemy.getHealth());
        assertEquals(vertexCalculator.triangle(10, position), enemy.getPosition2Ds());
        assertTrue(enemy.isAlive());
    }

    /**
     * Test the correct creation of entity Obstacle.
     */
    @Test
    public void testObstacle() {
        Position2D position = new Position2DImpl(100, 100);
        CachedGameEntity obstacle = entityFactory.createObstacle(10, position);
        assertNotNull(obstacle);
        assertEquals(position, obstacle.getPosition());
        assertEquals(vertexCalculator.square(10, position), obstacle.getPosition2Ds());
    }

    /**
     * Test the correct creation of entity Projectile.
     */
    @Test
    public void testProjectile() {
        Position2D position = new Position2DImpl(100, 100);
        Projectile projectile = entityFactory.createProjectile(CharacterType.RUNNER, position, new VectorImpl(1, 1), 5,
                10);
        assertNotNull(projectile);
        assertEquals(position, projectile.getPosition());
        assertEquals(vertexCalculator.square(5, position), projectile.getPosition2Ds());
        assertTrue(projectile.isAlive());
    }

}

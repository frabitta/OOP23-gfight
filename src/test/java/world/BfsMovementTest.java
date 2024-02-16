package world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.common.impl.VectorImpl;
import gfight.world.entity.api.ActiveEntity;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.EntityFactory;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.map.api.GameMap;
import gfight.world.map.impl.GameMapImpl;
import gfight.world.movement.impl.BfsMovement;

/**
 * Class that tests the correct movement of enemies.
 */
class BfsMovementTest {
    private final EntityFactory entityFactory = new EntityFactoryImpl();

    // CHECKSTYLE: MagicNumber OFF
    /**
     * Test the correct general functioning of the BfsMovement.
     */
    @Test
    void testBfsMovement() {
        final GameMap map = new GameMapImpl("map1");
        final ActiveEntity target = entityFactory.createChest(40, new Position2DImpl(180, 100), 50);
        final Character agent = entityFactory.createRunner(target, 25, new Position2DImpl(100, 100), 50, map);
        final BfsMovement bfs = new BfsMovement(agent, target, map, 1);

        bfs.update();
        assertEquals(agent.getDirection(), new VectorImpl(1, 0));
        final Position2D pos = new Position2DImpl(agent.getPosition().getX() + agent.getDirection().getX(),
                agent.getPosition().getY() + agent.getDirection().getY());
        agent.setPosition(pos);
        assertEquals(agent.getPosition(), pos);

        bfs.update();
        assertEquals(agent.getDirection(), new VectorImpl(1, 0));
        final Position2D pos1 = new Position2DImpl(agent.getPosition().getX() + agent.getDirection().getX(),
                agent.getPosition().getY() + agent.getDirection().getY());
        agent.setPosition(pos1);
        assertEquals(agent.getPosition(), pos1);
    }

    /**
     * Test the correct behavior of the Shooter enemy type.
     */
    @Test
    void testBfsShooter() {
        final GameMap map = new GameMapImpl("map1");
        final ActiveEntity target = entityFactory.createChest(40, new Position2DImpl(180, 100), 50);
        final Character agent = entityFactory.createShooter(target, 25, new Position2DImpl(100, 100), 50, map);
        final BfsMovement bfs = new BfsMovement(agent, target, map, 1);

        bfs.update();
        assertEquals(agent.getDirection(), new VectorImpl(0, 0));

        target.setPosition(new Position2DImpl(620, 100));
        for (int i = 0; i < 1000; i++) {
            bfs.update();
            final Position2D pos = new Position2DImpl(agent.getPosition().getX() + agent.getDirection().getX(),
                    agent.getPosition().getY() + agent.getDirection().getY());
            agent.setPosition(pos);
        }
        // The final position should be 3.5 tiles - 1 pixel (The width of a tile is 40)
        // far from the target
        assertEquals(agent.getPosition(), new Position2DImpl(481, 100));
    }

    /**
     * Test the correct behavior of the Shooter enemy type.
     */
    @Test
    void testBfsRunner() {
        final GameMap map = new GameMapImpl("map1");
        final ActiveEntity target = entityFactory.createChest(40, new Position2DImpl(620, 100), 50);
        final Character agent = entityFactory.createRunner(target, 25, new Position2DImpl(100, 100), 50, map);
        final BfsMovement bfs = new BfsMovement(agent, target, map, 1);

        for (int i = 0; i < 1000; i++) {
            bfs.update();
            final Position2D pos = new Position2DImpl(agent.getPosition().getX() + agent.getDirection().getX(),
                    agent.getPosition().getY() + agent.getDirection().getY());
            agent.setPosition(pos);
        }
        // The final position should be 1 tile (The width of a tile is 40) far from the
        // target
        // System.out.println(agent.getPosition());
        assertEquals(agent.getPosition(), new Position2DImpl(580, 100));
        assertEquals(agent.getDirection(), new VectorImpl(0, 0));
    }

}

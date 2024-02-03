package world;

import gfight.common.Position2D;
import gfight.common.impl.HitboxImpl;
import gfight.common.impl.Position2DImpl;
import gfight.world.hitbox.api.Hitboxes;
import gfight.world.hitbox.impl.HitboxesImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that tests hitboxes collision.
 */
public class HitboxTest {
    /*
     * test 2 known polygons collision.
     */
    // CHECKSTYLE: MagicNumber OFF
    @Test
    void testBox() {
        Hitboxes hitbox = new HitboxesImpl();
        List<Position2D> polygon1 = new ArrayList<>();
        polygon1.add(new Position2DImpl(0, 0));
        polygon1.add(new Position2DImpl(0, 2));
        polygon1.add(new Position2DImpl(2, 2));
        polygon1.add(new Position2DImpl(2, 0));

        List<Position2D> polygon2 = new ArrayList<>();

        polygon2.add(new Position2DImpl(1, 1));
        polygon2.add(new Position2DImpl(3, 1));
        polygon2.add(new Position2DImpl(1, 3));
        assertTrue(hitbox.isColliding(new HitboxImpl(polygon1), new HitboxImpl(polygon2)));

        List<Position2D> polygon3 = new ArrayList<>();

        polygon3.add(new Position2DImpl(0, 0));
        polygon3.add(new Position2DImpl(0, 3));
        polygon3.add(new Position2DImpl(3, 3));
        polygon3.add(new Position2DImpl(3, 0));

        List<Position2D> polygon4 = new ArrayList<>();

        polygon4.add(new Position2DImpl(4, 4));
        polygon4.add(new Position2DImpl(5, 5));
        polygon4.add(new Position2DImpl(3.1, 2));
        assertFalse(hitbox.isColliding(new HitboxImpl(polygon3), new HitboxImpl(polygon4)));
    }
}

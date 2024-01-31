package world;

import org.locationtech.jts.geom.Coordinate;
import gfight.world.impl.HitboxImpl;
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
    //CHECKSTYLE: MagicNumber OFF
    @Test
    void testBox() {
        HitboxImpl hitbox = new HitboxImpl();
        List<Coordinate> polygon1 = new ArrayList<>();
        polygon1.add(new Coordinate(0, 0));
        polygon1.add(new Coordinate(0, 2));
        polygon1.add(new Coordinate(2, 2));
        polygon1.add(new Coordinate(2, 0));
        
        List<Coordinate> polygon2 = new ArrayList<>();

        polygon2.add(new Coordinate(1, 1));
        polygon2.add(new Coordinate(3, 1));
        polygon2.add(new Coordinate(1, 3));
        assertTrue(hitbox.isColliding(hitbox.getGeometry(polygon1), hitbox.getGeometry(polygon2)));

        List<Coordinate> polygon3 = new ArrayList<>();

        polygon3.add(new Coordinate(0, 0));
        polygon3.add(new Coordinate(0, 3));
        polygon3.add(new Coordinate(3, 3));
        polygon3.add(new Coordinate(3, 0));

        List<Coordinate> polygon4 = new ArrayList<>();

        polygon4.add(new Coordinate(4, 4));
        polygon4.add(new Coordinate(5, 5));
        polygon4.add(new Coordinate(3.1, 2));
        assertFalse(hitbox.isColliding(hitbox.getGeometry(polygon3), hitbox.getGeometry(polygon4)));
    }
}

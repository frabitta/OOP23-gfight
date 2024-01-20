package world;

import gfight.world.Hitbox;
import org.locationtech.jts.geom.Coordinate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HitboxTest {
    /*
     * test 2 known polygons collision
     */
    @Test
    void testBox() {
        Hitbox hitbox = new Hitbox();
        assertTrue(hitbox.isColliding(
                hitbox.getGeometry(new Coordinate[] {
                        new Coordinate(0, 0),
                        new Coordinate(0, 2),
                        new Coordinate(2, 2),
                        new Coordinate(2, 0)
                }),
                hitbox.getGeometry(new Coordinate[] {
                        new Coordinate(1, 1),
                        new Coordinate(3, 1),
                        new Coordinate(1, 3)
                })));
        assertFalse(hitbox.isColliding(
                hitbox.getGeometry(new Coordinate[] {
                        new Coordinate(0, 0),
                        new Coordinate(0, 3),
                        new Coordinate(3, 3),
                        new Coordinate(3, 0)
                }),
                hitbox.getGeometry(new Coordinate[] {
                        new Coordinate(4, 4),
                        new Coordinate(5, 5),
                        new Coordinate(3.1, 2)
                })));
    }

}

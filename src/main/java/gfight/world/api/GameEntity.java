package gfight.world.api;

import java.util.Set;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.Coordinate;

public interface GameEntity {
    Polygon getHitBox();

    Set<GameEntity> getAllCollided();

    void reset();
}

package gfight.world.impl;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.List;

import gfight.world.Hitbox;
import gfight.world.api.GameEntity;
import java.util.ArrayList;

public class GameEntityImpl implements GameEntity {
    private final List<Coordinate> vertexes = new ArrayList<>();

    public GameEntityImpl(List<Coordinate> vertexes) {
        vertexes.addAll(vertexes);
    }

    @Override
    public Polygon getHitBox() {
        Hitbox hitbox = new Hitbox();
        return hitbox.getGeometry(vertexes);
    }
}

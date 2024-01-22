package gfight.world.impl;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.List;

import gfight.world.api.GameEntity;
import gfight.world.api.Hitbox;

import java.util.ArrayList;

public class GameEntityImpl implements GameEntity {
    private final List<Coordinate> vertexes = new ArrayList<>();
    private Coordinate position;

    public GameEntityImpl(List<Coordinate> vertexes) {
        vertexes.addAll(vertexes);
    }

    @Override
    public Polygon getHitBox() {
        Hitbox hitbox = new HitboxImpl();
        return hitbox.getGeometry(vertexes);
    }

    @Override
    public void setPosition(Coordinate position) {
        this.position = new Coordinate(position);
    }

    @Override
    public Coordinate getPosition() {
        return new Coordinate(position);
    }
}

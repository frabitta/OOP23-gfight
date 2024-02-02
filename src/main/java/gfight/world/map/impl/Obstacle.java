package gfight.world.map.impl;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.impl.CachedGameEntityImpl;

public class Obstacle extends CachedGameEntityImpl {

    public Obstacle(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent) {
        super(vertexes, position, graphicsComponent);
    }

    @Override
    public void reset() {
    }
}

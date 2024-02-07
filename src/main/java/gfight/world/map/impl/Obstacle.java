package gfight.world.map.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.entity.impl.CachedGameEntityImpl;

/**
 * A special implementation of a CachedGameEntity
 * which doesn't need to update its hitbox.
 */
public class Obstacle extends CachedGameEntityImpl {

    /**
     * Creates a CachedGameEntity representing and Obstacle in the map.
     * 
     * @param vertexes          the list of vertexes
     * @param position          the position of the center of the obstacle
     * @param graphicsComponent the graphics representing an obstacle
     */
    public Obstacle(
            final List<Position2D> vertexes, final Position2D position, final GraphicsComponent graphicsComponent) {
        super(vertexes, position, graphicsComponent);
    }

    @Override
    public void reset() {
    }
}

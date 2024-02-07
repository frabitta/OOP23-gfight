package gfight.world.map.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.impl.AbstractActiveEntity;

/**
 * An implementation of an ActiveEntity representing a chest.
 */
public class Chest extends AbstractActiveEntity {

    /**
     * Creates an ActiveEntity representing a chest.
     * 
     * @param vertexes          the list of vertexes
     * @param position          the position of the center of the chest
     * @param graphicsComponent the graphics representing the chest
     * @param health            the health of the chest
     */
    public Chest(
            final List<Position2D> vertexes,
            final Position2D position,
            final GraphicsComponent graphicsComponent,
            final int health) {
        super(vertexes, position, graphicsComponent, health);
        setMovement(Optional.empty());
    }

    @Override
    protected void applyCollisions(final Set<? extends GameEntity> gameobjects) {
        // does not collide :)
    }
}

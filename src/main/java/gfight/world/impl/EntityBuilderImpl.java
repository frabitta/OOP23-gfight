package gfight.world.impl;

import java.util.List;
import java.util.Optional;
import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.EntityBuilder;
import gfight.world.movement.api.Movement;

/**
 * This class implements a Builder of Entity.
 */
public final class EntityBuilderImpl implements EntityBuilder {
    private final List<Coordinate> vertexes;
    private final Coordinate position;
    private final GraphicsComponent graphicsComponent;
    private final int health;
    private Optional<Movement> movement = Optional.empty();

    /**
     * Constructor of EntityBuilderImpl.
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     * @param health
     */
    public EntityBuilderImpl(final List<Coordinate> vertexes, final Coordinate position,
            final GraphicsComponent graphicsComponent, final int health) {
        this.vertexes = vertexes;
        this.position = position;
        this.graphicsComponent = graphicsComponent;
        this.health = health;
    }

    @Override
    public EntityBuilder addMovement(final Movement movement) {
        this.movement = Optional.ofNullable(movement);
        return this;
    }

    @Override
    public ActiveEntityImpl build() throws IllegalStateException {
        if (this.vertexes == null || this.position == null || this.graphicsComponent == null) {
            throw new IllegalStateException();
        }
        return new ActiveEntityImpl(vertexes, position, graphicsComponent, movement, health);
    }
}

package gfight.world.impl;

import java.util.List;
import java.util.Optional;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.locationtech.jts.geom.Coordinate;
import gfight.common.api.GeomOperator;
import gfight.common.impl.GeomOperatorImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.movement.api.Movement;
import java.util.Set;

/**
 * An implementation of Moving Entity that also extends Cached Game Entity.
 */
public abstract class BaseMovingEntity extends CachedGameEntityImpl implements MovingEntity {
    private Optional<Movement> movement;

    /**
     * Moving Entity Updated Constructor with initial Movement.
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     * @param movement
     */
    public BaseMovingEntity(final List<Coordinate> vertexes, final Coordinate position,
            final GraphicsComponent graphicsComponent,
            final Optional<Movement> movement) {
        super(vertexes, position, graphicsComponent);
        this.movement = movement;
    }

    @Override
    public final Vector2D getDirection() {
        return movement.get().getDirection();
    }

    @Override
    public final void setDirection(final Vector2D direction) {
        movement.get().setDirection(direction);
    }

    @Override
    public final void updatePos(final long dt, final Set<GameEntity> gameobjects) {
        final double scalar = 0.0001;
        final GeomOperator calculator = new GeomOperatorImpl();
        movement.get().update(this);
        applyCollisions(gameobjects);
        setPosition(calculator.sum(getPosition(), getDirection().scalarMultiply(scalar)));
    }

    @Override
    public final void setMovement(final Optional<Movement> movement) {
        this.movement = movement;
    }

    /**
     * foreach element in collision it sets the type of collision and execute the
     * command.
     */
    protected abstract void applyCollisions(final Set<GameEntity> gameobjects);
}

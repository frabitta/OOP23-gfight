package gfight.world.entity.impl;

import java.util.List;
import java.util.Optional;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;
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
     */
    public BaseMovingEntity(final List<Position2D> vertexes, final Position2D position,
            final GraphicsComponent graphicsComponent) {
        super(vertexes, position, graphicsComponent);
    }

    @Override
    public final Vect getDirection() {
        return movement.get().getDirection();
    }

    @Override
    public final void setDirection(final Vect direction) {
        movement.get().setDirection(direction);
    }

    @Override
    public final void updatePos(final long dt, final Set<GameEntity> gameobjects) {
        final double scalar = 0.0001;
        if (movement.isPresent()) {
            movement.get().update();
            applyCollisions(gameobjects);
            setPosition(getPosition().sum(getDirection().scale(scalar * dt)));
            getGraphics().setPositions(getPosition2Ds());
        }
    }

    @Override
    public final void setMovement(final Optional<Movement> movement) {
        this.movement = movement;
    }

    /**
     * foreach element in collision it sets the type of collision and execute the
     * command.
     * 
     * @param gameobjects that are present in the game to handle collisions
     */
    protected abstract void applyCollisions(Set<GameEntity> gameobjects);
}

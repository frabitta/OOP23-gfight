package gfight.world.impl;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.LinkedHashSet;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.GameEntity;

/**
 * An implementation of the cached game entity.
 */
public abstract class CachedGameEntityImpl implements CachedGameEntity {

    private final GameEntity originalEntity;
    private Optional<Polygon> boundigBox;
    private Optional<Set<GameEntity>> collidedObjects;
    private boolean needResHitbox = false;
    private boolean needResCollided = false;

    /**
     * Chached Game Entity constructor.
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     */
    public CachedGameEntityImpl(final List<Coordinate> vertexes, final Coordinate position,
            final GraphicsComponent graphicsComponent) {
        originalEntity = new GameEntityImpl(vertexes, position, graphicsComponent);
        boundigBox = Optional.empty();
        collidedObjects = Optional.empty();
    }

    @Override
    public final Polygon getHitBox() {
        if (needResHitbox || boundigBox.isEmpty()) {
            needResHitbox = false;
            boundigBox = Optional.of(originalEntity.getHitBox());
        }
        return boundigBox.get();
    }

    @Override
    public final void reset() {
        needResHitbox = true;
        needResCollided = true;
    }

    @Override
    public final Set<GameEntity> getAllCollided(final Set<GameEntity> gameObjects) {
        if (needResCollided || collidedObjects.isEmpty()) {
            needResCollided = false;
            collidedObjects = Optional.of(originalEntity.getAllCollided(gameObjects));
        }
        return new LinkedHashSet<>(collidedObjects.get());
    }

    @Override
    public final void setPosition(final Coordinate position) {
        originalEntity.setPosition(position);
    }

    @Override
    public final Coordinate getPosition() {
        return originalEntity.getPosition();
    }

    @Override
    public final void setIgnoredEntities(final Set<GameEntity> ignoredEntities) {
        originalEntity.setIgnoredEntities(ignoredEntities);
    }

    @Override
    public final GraphicsComponent getGraphics() {
        return originalEntity.getGraphics();
    }

}

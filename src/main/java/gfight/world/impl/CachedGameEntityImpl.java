package gfight.world.impl;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.LinkedHashSet;

import gfight.view.GraphicsComponent;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.GameEntity;

public class CachedGameEntityImpl implements CachedGameEntity {

    private final GameEntity originalEntity;
    private Optional<Polygon> boundigBox;
    
    private Optional<Set<GameEntity>> collidedObjects;
    private boolean needResHitbox = false;
    private boolean needResCollided = false;

    public CachedGameEntityImpl(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent) {
        originalEntity = new GameEntityImpl(vertexes, position, graphicsComponent);
        boundigBox = Optional.empty();
        collidedObjects = Optional.empty();
    }

    @Override
    public Polygon getHitBox() {
        if (needResHitbox || boundigBox.isEmpty()) {
            needResHitbox = false;
            boundigBox = Optional.of(originalEntity.getHitBox());
        }
        return boundigBox.get();
    }

    @Override
    public void reset() {
        needResHitbox = true;
        needResCollided = true;
    }

    @Override
    public Set<GameEntity> getAllCollided(Set<GameEntity> gameObjects) {
        if (needResCollided || collidedObjects.isEmpty()) {
            needResCollided = false;
            collidedObjects = Optional.of(originalEntity.getAllCollided(gameObjects));
        }
        return new LinkedHashSet<>(collidedObjects.get());
    }

    @Override
    public void setPosition(Coordinate position) {
        originalEntity.setPosition(position);
    }

    @Override
    public Coordinate getPosition() {
        return originalEntity.getPosition();
    }

    @Override
    public void setIgnoredEntities(Set<GameEntity> ignoredEntities) {
        originalEntity.setIgnoredEntities(ignoredEntities);
    }
}

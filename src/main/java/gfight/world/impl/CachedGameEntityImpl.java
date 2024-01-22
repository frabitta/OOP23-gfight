package gfight.world.impl;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.LinkedHashSet;

import gfight.world.api.CachedGameEntity;
import gfight.world.api.GameEntity;
import gfight.world.api.Hitbox;

public abstract class CachedGameEntityImpl implements CachedGameEntity {

    private final GameEntity originalEntity;
    private Optional<Polygon> boundigBox;
    private final Set<CachedGameEntity> collidedObjectes;
    private boolean needResHitbox = false;
    private boolean needResCollided = false;

    public CachedGameEntityImpl(List<Coordinate> vertexes) {
        originalEntity = new GameEntityImpl(vertexes);
        boundigBox = Optional.empty();
        collidedObjectes = new LinkedHashSet<>();
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
    public Set<CachedGameEntity> getAllCollided(Set<CachedGameEntity> gameObjects) {
        if (needResCollided || collidedObjectes.isEmpty()) {
            needResCollided = false;
            allCollisions(gameObjects);
        }
        return collidedObjectes;
    }

    private void allCollisions(Set<CachedGameEntity> gameObjects) {
        collidedObjectes.clear();
        final Hitbox hitbox = new HitboxImpl();
        gameObjects.stream()
                .filter(a -> !a.equals(this) && hitbox.isColliding(this.getHitBox(), a.getHitBox()))
                .forEach(entity -> collidedObjectes.add(entity));
    }
}

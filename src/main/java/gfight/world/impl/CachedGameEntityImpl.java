package gfight.world.impl;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.LinkedHashSet;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.GameEntity;

public class CachedGameEntityImpl implements CachedGameEntity {

    private final GameEntity originalEntity;
    private Optional<Polygon> boundigBox;
    private final Set<GameEntity> collidedObjectes;
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
    public Set<GameEntity> getAllCollided(Set<GameEntity> gameObjects) {
        if (needResCollided || collidedObjectes.isEmpty()) {
            needResCollided = false;
            collidedObjectes.clear();
            collidedObjectes.addAll(originalEntity.getAllCollided(gameObjects));
        }
        return collidedObjectes;
    }

    @Override
    public void setPosition(Coordinate position) {
        originalEntity.setPosition(position);;
    }

    @Override
    public Coordinate getPosition() {
        return originalEntity.getPosition();
    }
}

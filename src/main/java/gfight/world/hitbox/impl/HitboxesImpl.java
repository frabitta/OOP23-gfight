package gfight.world.hitbox.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.prep.PreparedPolygon;
import org.locationtech.jts.geom.util.AffineTransformation;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.world.entity.api.CachedGameEntity;
import gfight.world.entity.api.GameEntity;
import gfight.world.hitbox.api.Hitbox;
import gfight.world.hitbox.api.Hitboxes;

/**
 * An implementation of Hitbox Interface.
 */
public final class HitboxesImpl implements Hitboxes {


    @Override
    public boolean isColliding(final Hitbox collider, final Hitbox coollided) {
        final PreparedPolygon myObject = new PreparedPolygon(collider.getPolygonalHitbox());
        return myObject.intersects(coollided.getPolygonalHitbox());
    }

    @Override
    public List<Position2D> rotate(final List<Position2D> polygon, final double theta) {
        if (polygon.isEmpty()) {
            return new ArrayList<>();
        }
        Hitbox hitbox = new HitboxImpl(polygon);
        AffineTransformation rotation = AffineTransformation.rotationInstance(theta);
        Coordinate[] rotatedCoordinates = rotation.transform(hitbox.getPolygonalHitbox()).getCoordinates();
        return Arrays.stream(rotatedCoordinates)
                .map(coordinate -> new Position2DImpl(coordinate.getX(), coordinate.getY()))
                .collect(Collectors.toList());
    }

    @Override
    public void freeHitboxes(final Set<CachedGameEntity> gaemobjects) {
        gaemobjects.stream().forEach(CachedGameEntity::reset);
    }

    @Override
    public Map<GameEntity, Set<GameEntity>> getAllCollision(final Set<GameEntity> gameObjects) {
        final Map<GameEntity, Set<GameEntity>> collisionMap = new LinkedHashMap<>();
        gameObjects.stream().forEach(entity -> collisionMap.put(entity, entity.getAllCollided(gameObjects)));
        return collisionMap;
    }
}

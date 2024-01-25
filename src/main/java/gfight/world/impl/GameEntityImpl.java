package gfight.world.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

import gfight.common.api.GeomOperator;
import gfight.common.impl.GeomOperatorImpl;
import gfight.world.api.GameEntity;
import gfight.world.api.Hitbox;

import java.util.ArrayList;

public class GameEntityImpl implements GameEntity {
    private final List<Coordinate> vertexes = new ArrayList<>();
    private Coordinate position;
    private final Set<GameEntity> ignoredEntities = new LinkedHashSet<>();

    public GameEntityImpl(List<Coordinate> vertexes) {
        vertexes.addAll(vertexes);
    }

    public GameEntityImpl(List<Coordinate> vertexes, Set<GameEntity> ignoredEntities) {
        this.ignoredEntities.addAll(ignoredEntities);
        vertexes.addAll(vertexes);
    }

    @Override
    public Polygon getHitBox() {
        Hitbox hitbox = new HitboxImpl();
        return hitbox.getGeometry(vertexes);
    }

    @Override
    public void setPosition(Coordinate position) {
        GeomOperator calculator = new GeomOperatorImpl();
        Vector2D distance = calculator.distance(position, this.position);
        vertexes.stream().map(vetex -> calculator.sum(vetex, distance));
        this.position = new Coordinate(position);
    }

    @Override
    public Coordinate getPosition() {
        return new Coordinate(position);
    }

    @Override
    public Set<GameEntity> getAllCollided(Set<GameEntity> gameObjects) {
        final Hitbox hitbox = new HitboxImpl();
        final Polygon boundingBox = this.getHitBox();
        final Set<GameEntity> collidedObjectes = new LinkedHashSet<>();
        gameObjects.stream()
                .filter(a -> !a.equals(this) && hitbox.isColliding(boundingBox, a.getHitBox())
                        && !ignoredEntities.contains(a))
                .forEach(entity -> collidedObjectes.add(entity));
        return collidedObjectes;
    }
}

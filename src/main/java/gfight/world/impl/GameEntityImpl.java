package gfight.world.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

import gfight.common.api.GeomOperator;
import gfight.common.impl.GeomOperatorImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.GameEntity;
import gfight.world.api.Hitbox;

import java.util.ArrayList;

/**
 * Implementation of Game Entity.
 */
public final class GameEntityImpl implements GameEntity {
    private List<Coordinate> vertexes = new ArrayList<>();
    private Coordinate position;
    private final GraphicsComponent graphicsComponent;
    private Set<GameEntity> ignoredEntities = new LinkedHashSet<>();

    /**
     * Game Entity constructor that creates gameEntity with vertexes position and
     * graphic component.
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     */
    public GameEntityImpl(final List<Coordinate> vertexes, final Coordinate position,
            final GraphicsComponent graphicsComponent) {
        this.graphicsComponent = graphicsComponent;
        this.position = position;
        vertexes.addAll(vertexes);
    }

    @Override
    public Polygon getHitBox() {
        Hitbox hitbox = new HitboxImpl();
        return hitbox.getGeometry(vertexes);
    }

    @Override
    public void setPosition(final Coordinate position) {
        final GeomOperator calculator = new GeomOperatorImpl();
        final Vector2D distance = calculator.distance(position, this.position);
        vertexes.stream().map(vetex -> calculator.sum(vetex, distance));
        this.position = new Coordinate(position);
    }

    @Override
    public Coordinate getPosition() {
        return new Coordinate(position);
    }

    @Override
    public Set<GameEntity> getAllCollided(final Set<GameEntity> gameObjects) {
        final Hitbox hitbox = new HitboxImpl();
        final Polygon boundingBox = this.getHitBox();
        final Set<GameEntity> collidedObjectes = new LinkedHashSet<>();
        gameObjects.stream()
                .filter(a -> !a.equals(this) && hitbox.isColliding(boundingBox, a.getHitBox())
                        && !ignoredEntities.contains(a))
                .forEach(entity -> collidedObjectes.add(entity));
        return collidedObjectes;
    }

    @Override
    public void setIgnoredEntities(final Set<GameEntity> ignoredEntities) {
        this.ignoredEntities.clear();
        this.ignoredEntities.addAll(ignoredEntities);
    }

    @Override
    public GraphicsComponent getGraphics() {
        return graphicsComponent;
    }

    @Override
    public List<Coordinate> getCoordinates() {
        return List.copyOf(vertexes);
    }

    @Override
    public void setCoordinates(final List<Coordinate> vertexes) {
        this.vertexes = vertexes;
    }

}

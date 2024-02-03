package gfight.world.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import gfight.common.Position2D;
import gfight.common.api.GeomOperator;
import gfight.common.api.Hitbox;
import gfight.common.impl.GeomOperatorImpl;
import gfight.common.impl.HitboxImpl;
import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.GameEntity;
import gfight.world.hitbox.api.Hitboxes;
import gfight.world.hitbox.impl.HitboxesImpl;

import java.util.ArrayList;

/**
 * Implementation of Game Entity.
 */
public final class GameEntityImpl implements GameEntity {
    private List<Position2D> vertexes = new ArrayList<>();
    private Position2D position;
    private final GraphicsComponent graphicsComponent;
    private final Set<GameEntity> ignoredEntities = new LinkedHashSet<>();

    /**
     * Game Entity constructor that creates gameEntity with vertexes position and
     * graphic component.
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     */
    public GameEntityImpl(final List<Position2D> vertexes, final Position2D position,
            final GraphicsComponent graphicsComponent) {
        this.graphicsComponent = graphicsComponent;
        this.position = position;
        vertexes.addAll(vertexes);
    }

    @Override
    public Hitbox getHitBox() {
        return new HitboxImpl(vertexes);
    }

    @Override
    public void setPosition(final Position2D position) {
        final GeomOperator calculator = new GeomOperatorImpl();
        final Vector2D distance = calculator.distance(position, this.position);
        vertexes.stream().map(vetex -> calculator.sum(vetex, distance));
        this.position = new Position2DImpl(position);
    }

    @Override
    public Position2D getPosition() {
        return new Position2DImpl(position);
    }

    @Override
    public Set<GameEntity> getAllCollided(final Set<GameEntity> gameObjects) {
        final Hitboxes hitbox = new HitboxesImpl();
        final Hitbox boundingBox = this.getHitBox();
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
    public List<Position2D> getPosition2Ds() {
        return List.copyOf(vertexes);
    }

    @Override
    public void setCoordinates(final List<Position2D> vertexes) {
        this.vertexes = vertexes;
    }
}

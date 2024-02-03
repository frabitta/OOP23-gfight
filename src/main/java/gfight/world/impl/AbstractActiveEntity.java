package gfight.world.impl;

import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.ActiveEntity;

/**
 * This class implements the concept of ActiveEntity with movement and health
 * (Chest, Player and Enemies).
 */
public abstract class AbstractActiveEntity extends BaseMovingEntity implements ActiveEntity {
    private int health;

    /**
     * Constructor of ActiveEntityImpl.
     * 
     * @param vertexes          of the shape
     * @param position          the center of the shape
     * @param graphicsComponent the color of the shape
     * @param health            of the entity
     */
    public AbstractActiveEntity(final List<Coordinate> vertexes, final Coordinate position,
            final GraphicsComponent graphicsComponent, final int health) {
        super(vertexes, position, graphicsComponent);
        this.health = health;
    }

    @Override
    public final int getHealth() {
        return this.health;
    }

    @Override
    public final void takeDamage(final int damage) {
        this.setHealth(getHealth() - damage);
    }

    /**
     * Set the life point of the entity
     * 
     * @param health the actual health of the entity
     */
    private final void setHealth(final int health) {
        this.health = health;
    }

}

package gfight.world.impl;

import java.util.Optional;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.collision.api.CollisionCommand;
import gfight.world.collision.impl.SlideCommand;
import gfight.world.movement.api.Movement;

/**
 * This class implements the concept of ActiveEntity with movement and health (Chest, Player and Enemies).
 */
public abstract class ActiveEntityImpl extends BaseMovingEntity implements ActiveEntity {
    private int health;

    /** 
     * Constructor of ActiveEntityImpl.
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     * @param movement
     * @param health
     */
    public ActiveEntityImpl(final List<Coordinate> vertexes, final Coordinate position,
            final GraphicsComponent graphicsComponent,
            final Optional<Movement> movement, final int health) {
        super(vertexes, position, graphicsComponent, movement);
        this.health = health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(final int health) {
        this.health = health;
    }


}

package gfight.world.impl;

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.locationtech.jts.geom.Coordinate;
import gfight.common.api.GeomOperator;
import gfight.common.impl.GeomOperatorImpl;
import gfight.world.api.MovingEntity;
import gfight.world.movement.api.Movement;

public abstract class BaseMovingEntity extends CachedGameEntityImpl implements MovingEntity {
    private Movement movement;

    public BaseMovingEntity(List<Coordinate> vertexes, Movement movement) {
        super(vertexes);
        this.movement = movement;
    }

    @Override
    public Vector2D getDirection() {
        return movement.getDirection();
    }

    @Override
    public void setDirection(Vector2D direction) {
        movement.setDirection(direction);
    }

    @Override
    public void updatePos(long dt) {
        GeomOperator calculator = new GeomOperatorImpl();
        movement.update();
        applyCollisions();
        setPosition(calculator.sum(getPosition(), getDirection().scalarMultiply(0.0001)));
    }

    /**
     * foreach element in collision it sets the type of collision and execute the
     * command
     */
    abstract protected void applyCollisions();
    /*
     * {
     * collidedObjectes.stream().forEach(el -> {
     * if (el instanceof GameEntity) {
     * CollisionCommand coll = new SlideCommand<MovingEntity,GameEntity>(this, el);
     * coll.execute();
     * }
     * });
     * }
     */
}

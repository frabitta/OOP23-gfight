package gfight.world.collision.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import gfight.common.api.GeomOperator;
import gfight.common.impl.GeomOperatorImpl;
import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;

/**
 * This type of collision change the direction of the moving entity in order to
 * move away from the other entity.
 * 
 * @param <M> is the entitiy that moves and causes the collision
 * @param <G> is the other entity
 */
public final class SlideCommand<M extends MovingEntity, G extends GameEntity> extends AbstractCollisionCommand<M, G> {

    /**
     * 
     * @param outercollider
     * @param outercollided
     */
    public SlideCommand(final M outercollider, final G outercollided) {
        super(outercollider, outercollided);
    }

    @Override
    public void execute() {
        final GeomOperator operator = new GeomOperatorImpl();
        final Vector2D distance = operator.distance(collider().getPosition(), collided().getPosition());
        if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
            collider().setDirection(new Vector2D(-collider().getDirection().getX(), collider().getDirection().getY()));
        } else {
            collider().setDirection(new Vector2D(collider().getDirection().getX(), -collider().getDirection().getY()));
        }
    }

}

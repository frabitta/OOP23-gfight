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
 * @param <ME> is the entitiy that moves and causes the collision
 * @param <GE> is the other entity
 */
public class SlideCommand<ME extends MovingEntity, GE extends GameEntity> extends AbstractCollisionCommand<ME, GE> {

    public SlideCommand(final ME outercollider, final GE outercollided) {
        super(outercollider, outercollided);
    }

    @Override
    public void execute() {
        GeomOperator operator = new GeomOperatorImpl();
        Vector2D distance = operator.distance(collider().getPosition(), collided().getPosition());
        if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
            collider().setDirection(new Vector2D(-collider().getDirection().getX(), collider().getDirection().getY()));
        } else {
            collider().setDirection(new Vector2D(collider().getDirection().getX(), -collider().getDirection().getY()));
        }
    }

}

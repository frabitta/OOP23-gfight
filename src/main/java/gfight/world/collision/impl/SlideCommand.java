package gfight.world.collision.impl;

import gfight.common.api.Vect;
import gfight.common.impl.VectorImpl;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;

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
        final Vect distance = new VectorImpl(collider().getPosition(), collided().getPosition());
        if (collider().getDirection().dotProduct(distance) > 0) {
            if (Math.abs(distance.getX() - distance.getY()) > 0.3) {
                Vect nextDir;
                if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
                    nextDir = new VectorImpl(-collider().getDirection().getX(), collider().getDirection().getY());
                } else {
                    nextDir = new VectorImpl(collider().getDirection().getX(), -collider().getDirection().getY());
                }
                collider().setDirection(nextDir);
            }
        }
    }
}
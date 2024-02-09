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
public final class PushAwayCommand<M extends MovingEntity, G extends GameEntity>
        extends AbstractCollisionCommand<M, G> {

    /**
     * 
     * @param outercollider
     * @param outercollided
     */
    public PushAwayCommand(final M outercollider, final G outercollided) {
        super(outercollider, outercollided);
    }

    @Override
    public void execute() {
        final Vect distance = new VectorImpl(collided().getPosition(), collider().getPosition());
        collider().setDirection(distance.norm());
    }
}

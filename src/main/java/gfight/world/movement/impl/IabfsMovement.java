package gfight.world.movement.impl;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;

public class IabfsMovement extends BaseMovement {
    private final GameEntity target;

    public IabfsMovement( final GameEntity target) {
        this.target = target;
    }

    @Override
    public void update(final MovingEntity agent) {

    }

}

package gfight.world.movement.impl;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;

public class IabfsMovement extends BaseMovement {
    private final MovingEntity agent;
    private final GameEntity target;

    public IabfsMovement(final MovingEntity agent, final GameEntity target) {
        this.agent = agent;
        this.target = target;
    }

    @Override
    public void update() {

    }

}

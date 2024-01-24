package gfight.world.impl;

import gfight.world.api.CollisionCommand;
import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;

public abstract class AbstractCollisionCommand<ME extends MovingEntity, GE extends GameEntity>
        implements CollisionCommand<ME, GE> {
    protected final ME collider;
    protected final GE collided;

    public AbstractCollisionCommand(ME outercollider, GE outercollided) {
        this.collided = outercollided;
        this.collider = outercollider;
    }
}

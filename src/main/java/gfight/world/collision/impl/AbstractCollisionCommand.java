package gfight.world.collision.impl;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.collision.api.CollisionCommand;

public abstract class AbstractCollisionCommand<ME extends MovingEntity, GE extends GameEntity>
        implements CollisionCommand<ME, GE> {
    protected final ME collider;
    protected final GE collided;

    public AbstractCollisionCommand(ME outercollider, GE outercollided) {
        this.collided = outercollided;
        this.collider = outercollider;
    }
}

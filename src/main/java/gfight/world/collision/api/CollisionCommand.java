package gfight.world.collision.api;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;

public interface CollisionCommand<ME extends MovingEntity, GE extends GameEntity>{
    //TODO some collision could remove life ecc.. see patthern command

    void execute();
}

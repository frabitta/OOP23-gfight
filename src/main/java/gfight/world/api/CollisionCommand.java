package gfight.world.api;

public interface CollisionCommand<ME extends MovingEntity, GE extends GameEntity>{
    //TODO some collision could remove life ecc.. see patthern command

    void execute();
}

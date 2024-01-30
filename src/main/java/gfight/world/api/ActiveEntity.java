package gfight.world.api;

public interface ActiveEntity extends AliveEntity, MovingEntity{
    void rotate();

    void shoot();
}

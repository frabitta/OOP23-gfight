package gfight.world.api;

public interface ActiveEntity extends MovingEntity{
    
    int getHealth();

    void setHealth(int health);
    
    void rotate();

    void shoot();    
}

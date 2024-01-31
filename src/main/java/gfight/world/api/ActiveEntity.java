package gfight.world.api;
/**
 * Interface that represents the concept of ActiveEntity
 */
public interface ActiveEntity extends MovingEntity {
    
    int getHealth();

    void setHealth(int health);

}

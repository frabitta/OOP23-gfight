package gfight.world.api;

public interface AliveEntity extends CachedGameEntity{
    /**
     * 
     * @return life of the entity
     */
    int getHealth();

    /**
     * Set health of the entity
     * @param health
     */
    void setHealth(int health);
}

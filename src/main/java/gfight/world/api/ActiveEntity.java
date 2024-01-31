package gfight.world.api;
/**
 * Interface that represents the concept of ActiveEntity.
 */
public interface ActiveEntity extends MovingEntity {
    /**
     * 
     * @return health of the entity
     */
    int getHealth();

    /**
     * Set health of the entity.
     * @param health
     */
    void setHealth(int health);

}

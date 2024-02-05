package gfight.world.entity.api;

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
     * 
     * @param damage of the weapon
     */
    void takeDamage(int damage);

}

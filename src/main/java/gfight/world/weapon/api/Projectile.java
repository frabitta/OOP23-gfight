package gfight.world.weapon.api;

import gfight.world.entity.api.MovingEntity;

/**
 * Interface of a projectile.
 */
public interface Projectile extends MovingEntity{

    /**
     * Sets the damage of the projectile.
     * @param damage
     */
    void setDamage(int damage);
}

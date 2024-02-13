package gfight.world.weapon.api;

import gfight.world.entity.api.ActiveEntity;

/**
 * Interface of a projectile.
 */
public interface Projectile extends ActiveEntity {

    /**
     * Sets the damage of the projectile.
     * @param damage damage value
     */
    void setDamage(int damage);
}

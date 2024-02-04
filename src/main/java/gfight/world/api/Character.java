package gfight.world.api;

import gfight.world.weapon.api.Weapon;

public interface Character extends ActiveEntity {
    /**
     * Perform the action of rotating.
     * 
     * @param theta the angle of rotation
     */
    void rotate(double theta);

    /**
     * Perform the action of makingDamage.
     */
    void makeDamage();

    /**
     * Set the weapon of the entity
     * @param weapon
     */
    void setWeapon(Weapon weapon);

}

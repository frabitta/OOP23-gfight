package gfight.world.weapon.api;

import gfight.world.entity.api.Character;

/**
 * Interface of a generic Weapon.
 */
public interface Weapon {

    /**
     * Sets the entity in possesion of the weapon.
     * @param parent parent Character that has control of the weapon
     */
    void setParentEntity(Character parent);

    /**
     * Lets the weapon perform the attack.
     */
    void attack();

}

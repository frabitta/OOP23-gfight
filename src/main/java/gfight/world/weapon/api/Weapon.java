package gfight.world.weapon.api;

import gfight.world.api.ActiveEntity;

/**
 * Interface of a generic Weapon.
 */
public interface Weapon {

    /**
     * Sets the entity in possesion of the weapon.
     * @param parent
     */
    void setParentEntity(ActiveEntity parent);

    /**
     * Lets the weapon perform the attack.
     */
    Projectile shoot();

    /**
     * Sets the team of the weapon.
     * This is here in order to prevent "Friendly Fire".
     * @param team
     */
    void setTeam(Team team);
}

package gfight.world.weapon.api;

import gfight.world.api.ActiveEntity;

/**
 * Interface of a generic Weapon.
 */
public interface Weapon {

    /**
     * Enum that describes the two possible teams in the game.
     */
    enum Team {
        PLAYER, ENEMY
    }

    /**
     * Sets the entity in possesion of the weapon.
     * @param parent
     */
    void setParentEntity(ActiveEntity parent);

    /**
     * Lets the weapon perform the attack.
     */
    void shoot();

    /**
     * Sets the team of the weapon.
     * This is here in order to prevent "Friendly Fire".
     * @param team
     */
    void setTeam(Team team);
}

package gfight.world.entity.api;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.world.weapon.api.Weapon;

/**
 * This interface represent the concept of Character (Enemies and Player).
 */
public interface Character extends ActiveEntity {
    /**
     * Enum representing the types of characters in the game.
     * It includes Player and Enemy as possible values.
     */
    enum CharacterType {
        /**
         * Represents the player character.
         */
        PLAYER,
        /**
         * Represents an enemy character.
         */
        ENEMY
    }

    /**
     * Perform the action of pointing to the given target.
     * 
     * @param target the target position to point to
     */
    void pointTo(Position2D target);

    /**
     * Perform the action of makingDamage.
     */
    void makeDamage();

    /**
     * Set the weapon of the entity.
     * 
     * @param weapon
     */
    void setWeapon(Weapon weapon);

    /**
     * 
     * @return direction to which the entity is facing
     */
    Vect getPointedDirection();

    /**
     * Set the direction to which the entity is facing.
     * 
     * @param pointingDirection
     */
    void setPointingDirection(Vect pointingDirection);

    /**
     * 
     * @return the type of the entity
     */
    CharacterType getType();

}

package gfight.world.weapon.api;

import gfight.world.entity.api.EntityFactory;
import gfight.world.entity.api.Character;

/**
 * Factory of weapon types, and other utilities.
 */
public interface WeaponFactory {

    /**
     * Pairs a weapon with a character.
     * @param weapon    Weapon to give to the character.
     * @param character Character that has to hold the gun.
     */
    void pair(Weapon weapon, Character character);

    /**
     * Returns a gun that shoot projectiles.
     * @param reloadTime        Time between each shoot.
     * @param shootSpeed        Speed of the shooted projectile.
     * @param projectileSize    Size of the projectile.
     * @param damage            damage of the projectile.
     * @param projectileFactory Factory of entities (needed to generate projectiles).
     * @return Weapon of type SimpleGun
     */
    Weapon simpleGun(long reloadTime, long shootSpeed, double projectileSize, int damage, EntityFactory projectileFactory);

    /**
     * Returns a gun that shoot projectiles and pairs it with the Character.
     * @param reloadTime        Time between each shoot.
     * @param shootSpeed        Speed of the shooted projectile.
     * @param projectileSize    Size of the projectile.
     * @param damage            damage of the projectile.
     * @param projectileFactory Factory of entities (needed to generate projectiles).
     * @param character            The Character holding the gun.
     * @return Weapon of type SimpleGun
     */
    Weapon simpleGunPairing(
        long reloadTime,
        long shootSpeed,
        double projectileSize,
        int damage,
        EntityFactory projectileFactory,
        Character character);

}

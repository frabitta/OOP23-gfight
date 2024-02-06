package gfight.world.weapon.api;

import gfight.world.entity.api.EntityFactory;
import gfight.world.entity.api.Character;

/**
 * Factory of weapon types.
 */
public interface WeaponFactory {

    /**
     * Returns a gun that shoot projectiles.
     * @param reloadTime        Time between each shoot.
     * @param projectileFactory Factory of entities (needed to generate projectiles).
     * @param parent            The Character holding the gun.
     * @return Weapon of type SimpleGun
     */
    Weapon simpleGun(final long reloadTime, EntityFactory projectileFactory, Character parent);
}

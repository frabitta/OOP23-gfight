package gfight.world.weapon.impl;

import gfight.world.entity.api.EntityFactory;
import gfight.world.weapon.api.Weapon;
import gfight.world.weapon.api.WeaponFactory;
import gfight.world.entity.api.Character;

/**
 * Implementation of the factory of Weapons with the ability to pair a weapon with a Character.
 */
public final class WeaponFactoryImpl implements WeaponFactory {

    @Override
    public void pair(final Weapon weapon, final Character character) {
        character.setWeapon(weapon);
    }

    @Override
    public Weapon simpleGun(
        final long reloadTime,
        final long shootSpeed,
        final double projectileSize,
        final int damage,
        final EntityFactory projectileFactory) {
        return new SimpleGun(reloadTime, shootSpeed, projectileSize, damage, projectileFactory);
    }

    @Override
    public Weapon simpleGunPairing(
        final long reloadTime,
        final long shootSpeed,
        final double projectileSize,
        final int damage,
        final EntityFactory projectileFactory,
        final Character character) {
        final Weapon gun = simpleGun(reloadTime, shootSpeed, projectileSize, damage, projectileFactory);
        pair(gun, character);
        return gun;
    }

}

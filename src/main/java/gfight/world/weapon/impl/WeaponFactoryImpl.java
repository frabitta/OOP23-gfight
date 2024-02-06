package gfight.world.weapon.impl;

import gfight.world.entity.api.EntityFactory;
import gfight.world.weapon.api.Weapon;
import gfight.world.weapon.api.WeaponFactory;
import gfight.world.entity.api.Character;

public class WeaponFactoryImpl implements WeaponFactory {

    @Override
    public void pair(final Weapon weapon, final Character character) {
        character.setWeapon(weapon);
    }
    
    @Override
    public Weapon simpleGun(final long reloadTime, final long shootSpeed, final double projectileSize, final int damage, EntityFactory projectileFactory) {
        return new SimpleGun(reloadTime, shootSpeed, projectileSize, damage, projectileFactory);
    }

    @Override
    public Weapon simpleGunPairing(final long reloadTime, final long shootSpeed, final double projectileSize, final int damage, EntityFactory projectileFactory, final Character character) {
        Weapon gun = simpleGun(reloadTime, shootSpeed, projectileSize, damage, projectileFactory);
        pair(gun, character);
        return gun;
    }

}
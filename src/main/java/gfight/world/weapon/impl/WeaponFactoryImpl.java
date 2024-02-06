package gfight.world.weapon.impl;

import gfight.world.entity.api.EntityFactory;
import gfight.world.weapon.api.Weapon;
import gfight.world.weapon.api.WeaponFactory;
import gfight.world.entity.api.Character;

public class WeaponFactoryImpl implements WeaponFactory {

    @Override
    public Weapon simpleGun(long reloadTime, EntityFactory projectileFactory, Character parent) {
        Weapon gun = new SimpleGun(reloadTime, projectileFactory);
        gun.setParentEntity(parent);
        parent.setWeapon(gun);
        return gun;
    }

}

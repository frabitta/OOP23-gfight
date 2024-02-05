package gfight.world.weapon.impl;

import gfight.world.entity.api.Character;
import gfight.world.entity.api.EntityFactory;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.weapon.api.Projectile;
import gfight.world.weapon.api.Weapon;

/**
 * Simple implementation of a gun.
 * It shoots projectiles if it has passed enough time since the last shoot.
 */
public class WeaponImpl implements Weapon {

    private final EntityFactory projectileFactory = new EntityFactoryImpl();
    private final long reloadTime;

    private Character parent;
    private long lastShootTime;

    public WeaponImpl(final long reloadTime) {
        this.reloadTime = reloadTime;
        this.lastShootTime = System.currentTimeMillis();
    }

    @Override
    public final void setParentEntity(final Character parent) {
        this.parent = parent;
    }

    @Override
    public final Projectile shoot() {
        if (!reloaded()) {
            return null;
        }
        this.lastShootTime = System.currentTimeMillis();
        // - change factory from wich to generate
        // (needs to be the one connected to the entityManager)
        // get it from the parent
        return projectileFactory.createProjectile(this.parent.getType(), this.parent.getPosition(), this.parent.getPointedDirection());
    }

    private boolean reloaded() {
        return System.currentTimeMillis() - this.lastShootTime >= this.reloadTime;
    }

}

package gfight.world.weapon.impl;

import gfight.world.entity.api.Character;
import gfight.world.entity.api.EntityFactory;
import gfight.world.weapon.api.Weapon;

/**
 * Simple implementation of a gun.
 * It shoots projectiles if it has passed enough time since the last shoot.
 */
public class SimpleGun implements Weapon {

    private final EntityFactory projectileFactory;
    private final long reloadTime;
    private final long shootSpeed;

    private Character parent;
    private long lastShootTime;

    public SimpleGun(final long reloadTime, final long shootSpeed, EntityFactory projectileFactory) {
        this.reloadTime = reloadTime;
        this.shootSpeed = shootSpeed;
        this.projectileFactory = projectileFactory;
        this.lastShootTime = System.currentTimeMillis();
    }

    @Override
    public final void setParentEntity(final Character parent) {
        this.parent = parent;
    }

    @Override
    public final void shoot() {
        if (reloaded()) {
            this.lastShootTime = System.currentTimeMillis();
            this.projectileFactory.createProjectile(this.parent.getType(), this.parent.getPosition(), this.parent.getPointedDirection().norm().scale(shootSpeed));
        }
    }

    private boolean reloaded() {
        return System.currentTimeMillis() - this.lastShootTime >= this.reloadTime;
    }

}

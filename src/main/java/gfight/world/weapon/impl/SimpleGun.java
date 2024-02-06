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
    private final double projectileSize;
    private final int damage;

    private Character parent;
    private long lastShootTime;

    public SimpleGun(final long reloadTime, final long shootSpeed, final double projectileSize, final int damage, EntityFactory projectileFactory) {
        this.reloadTime = reloadTime;
        this.shootSpeed = shootSpeed;
        this.projectileSize = projectileSize;
        this.damage = damage;
        this.projectileFactory = projectileFactory;
        this.lastShootTime = System.currentTimeMillis();
    }

    //  may expose internal representation by storing an externally mutable object into SimpleGun.parent
    @Override
    public final void setParentEntity(final Character parent) {
        this.parent = parent;
    }

    @Override
    public final void shoot() {
        if (reloaded()) {
            this.lastShootTime = System.currentTimeMillis();
            this.projectileFactory.createProjectile(
                this.parent.getType(),
                this.parent.getPosition(),
                this.parent.getPointedDirection().norm().scale(shootSpeed),
                this.projectileSize,
                this.damage
            );
        }
    }

    private boolean reloaded() {
        return System.currentTimeMillis() - this.lastShootTime >= this.reloadTime;
    }

}
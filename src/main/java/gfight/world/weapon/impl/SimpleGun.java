package gfight.world.weapon.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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

    /**
     * Constructor of a gun.
     * @param reloadTime        Time between each shoot
     * @param shootSpeed        Speed of the projectile
     * @param projectileSize    Size of the projectile
     * @param damage            Damage of the projectile
     * @param projectileFactory The generator of the projectile
     */
    public SimpleGun(
        final long reloadTime,
        final long shootSpeed,
        final double projectileSize,
        final int damage,
        final EntityFactory projectileFactory) {
        this.reloadTime = reloadTime;
        this.shootSpeed = shootSpeed;
        this.projectileSize = projectileSize;
        this.damage = damage;
        this.projectileFactory = projectileFactory;
        this.lastShootTime = System.currentTimeMillis();
    }

    @Override
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "Necessary to store the parent in order uniquely connect weapon and parent")
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

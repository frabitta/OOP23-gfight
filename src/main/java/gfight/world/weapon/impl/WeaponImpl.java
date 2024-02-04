package gfight.world.weapon.impl;

import gfight.world.api.ActiveEntity;
import gfight.world.api.EntityFactory;
import gfight.world.impl.EntityFactoryImpl;
import gfight.world.weapon.api.Team;
import gfight.world.weapon.api.Weapon;

public class WeaponImpl implements Weapon {

    private final EntityFactory projectileFactory = new EntityFactoryImpl();

    private ActiveEntity parent;
    private Team team;

    private long lastShootTime;
    private long reloadTime = 0;

    WeaponImpl(long reloadTime) {
        this.reloadTime = reloadTime;
        this.lastShootTime = System.currentTimeMillis();
    }

    @Override
    public void setParentEntity(ActiveEntity parent) {
        this.parent = parent;
    }

    @Override
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public void shoot() {
        if (!reloaded()) {
            return;
        }
        this.lastShootTime = System.currentTimeMillis();
        //-- instantiate the projectile
        // will need:
        // - position
        // - direction
        // - projectile tipe to shoot
        // - give the projectile to the world to track and update it
        projectileFactory.createProjectile(this.team, this.parent.getPosition(), this.parent.getDirection());
    }

    private boolean reloaded() {
        return System.currentTimeMillis() - this.lastShootTime >= this.reloadTime;
    }

}

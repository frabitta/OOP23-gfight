package gfight.world.weapon.impl;

import java.util.List;
import java.util.Set;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.GameEntity;
import gfight.world.impl.BaseMovingEntity;
import gfight.world.movement.api.Movement;
import gfight.world.weapon.api.Projectile;
import gfight.world.weapon.api.Team;

public class ProjectileImpl extends BaseMovingEntity implements Projectile {

    private int damage = 0;

    public ProjectileImpl(Coordinate position, Team team, Movement movement) {
        super(vertexes, position, graphicsComponent);
        this.setMovement(movement);
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    protected void applyCollisions(Set<GameEntity> gameobjects) {
        for (var entity: gameobjects) {
            if (entity instanceof ActiveEntity) {
                // - verifica il team di appartenenza
                // - arreca danno
            }
        }
    }

}

package gfight.world.weapon.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.GameEntity;
import gfight.world.impl.BaseMovingEntity;
import gfight.world.movement.api.Movement;
import gfight.world.weapon.api.Projectile;
import gfight.world.weapon.api.Team;

public class ProjectileImpl extends BaseMovingEntity implements Projectile {

    private int damage = 0;
    private final Team team;

    public ProjectileImpl(List<Position2D> vertexes, Position2D position, GraphicsComponent gComp, Team team, Movement movement) {
        super(vertexes, position, gComp);
        this.setMovement(Optional.ofNullable(movement));
        this.team = team;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    protected void applyCollisions(Set<GameEntity> gameobjects) {
        for (var entity: gameobjects) {
            if (entity instanceof ActiveEntity) {
                var activeEntity = ((ActiveEntity)entity);
                // - verify team
                activeEntity.takeDamage(damage);
            }
            // - if obstacle destroy the object
        }
    }

}

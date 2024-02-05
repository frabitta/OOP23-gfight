package gfight.world.weapon.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.Character;
import gfight.world.entity.impl.BaseMovingEntity;
import gfight.world.movement.api.Movement;
import gfight.world.weapon.api.Projectile;

/**
 * Implementation of a simple projectile.
 * It damages the ActiveEntities that come in touch with it and are of the opponent team.
 */
public class ProjectileImpl extends BaseMovingEntity implements Projectile {

    private int damage;
    private final Character.CharacterType team;

    /**
     * Constructor of a simple projectile.
     * @param vertexes vertexes of the projectile
     * @param position central position
     * @param gComp GraphicsComponent of the projectile
     * @param team Team who shoot the projectile
     * @param movement Movement of the projectile
     */
    public ProjectileImpl(
        final List<Position2D> vertexes,
        final Position2D position,
        final GraphicsComponent gComp,
        final Character.CharacterType team,
        final Movement movement
        ) {
        super(vertexes, position, gComp);
        this.setMovement(Optional.ofNullable(movement));
        this.team = team;
    }

    @Override
    public final void setDamage(final int damage) {
        this.damage = damage;
    }

    @Override
    protected final void applyCollisions(final Set<? extends GameEntity> gameobjects) {
        getAllCollided(gameobjects).stream()
            .filter(entity -> entity instanceof Character)
            .map(entity -> (Character) entity)
            .filter(entity -> entity.getType() != this.team)
            .forEach(ch -> ch.takeDamage(this.damage));
        // - destroy the object if on contact
        // - need to find a way to prevent a forever existing projectile (world closed by walls?)
    }
}
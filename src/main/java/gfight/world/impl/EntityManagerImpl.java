package gfight.world.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.world.api.EntityManager;
import gfight.world.entity.api.ActiveEntity;
import gfight.world.entity.api.CachedGameEntity;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.EntityFactory;
import gfight.world.entity.api.GameEntity;
import gfight.world.map.api.GameMap;
import gfight.world.movement.api.InputMovement;
import gfight.world.weapon.api.Projectile;

/**
 * Default implementation of an EntityManager.
 */
public class EntityManagerImpl implements EntityManager {

    private final EntityFactory factory;
    private final Set<CachedGameEntity> otherEntities;
    private final Set<CachedGameEntity> enemies;

    /**
     * Creates a new decorator for an entity factory
     * which stores every created game entity.
     * 
     * @param factory the entity factory to decorate
     */
    public EntityManagerImpl(final EntityFactory factory) {
        this.factory = factory;
        this.otherEntities = new HashSet<>();
        this.enemies = new HashSet<>();
    }

    @Override
    public Character createPlayer(
            final double sideLength, final Position2D position, final int health, final InputMovement movement) {
        final Character player = this.factory.createPlayer(sideLength, position, health, movement);
        this.otherEntities.add(player);
        return player;
    }

    @Override
    public Character createEnemy(final GameEntity target, final double sideLength, final Position2D position,
            final int health, final GameMap map) {
        final Character enemy = this.factory.createEnemy(target, sideLength, position, health, map);
        this.enemies.add(enemy);
        return enemy;
    }

    @Override
    public CachedGameEntity createObstacle(final double sideLength, final Position2D position) {
        final CachedGameEntity obstacle = this.factory.createObstacle(sideLength, position);
        this.otherEntities.add(obstacle);
        return obstacle;
    }

    @Override
    public ActiveEntity createChest(final double sideLength, final Position2D position, final int health) {
        final ActiveEntity chest = this.factory.createChest(sideLength, position, health);
        this.otherEntities.add(chest);
        return chest;
    }

    @Override
    public Projectile createProjectile(
        final Character.CharacterType team,
        final Position2D position,
        final Vect direction,
        final double projectileSize,
        final int damage) {
        final Projectile projectile = this.factory.createProjectile(team, position, direction, projectileSize, damage);
        this.otherEntities.add(projectile);
        return projectile;
    }

    @Override
    public Set<CachedGameEntity> getEntities() {
        return Stream.concat(this.enemies.stream(), this.otherEntities.stream()).collect(Collectors.toSet());
    }

    @Override
    public boolean isClear() {
        return this.enemies.isEmpty();
    }
}

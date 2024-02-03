package gfight.world.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.EntityFactory;
import gfight.world.api.GameEntity;
import gfight.world.api.VertexCalculator;
import gfight.world.map.impl.Obstacle;
import gfight.world.movement.api.InputMovement;
import gfight.world.movement.api.Movement;
import gfight.world.movement.impl.IabfsMovement;
import gfight.world.movement.impl.InputMovementImpl;
import gfight.world.weapon.api.Projectile;

/**
 * This class is a factory of enteties.
 */
public class EntityFactoryImpl implements EntityFactory {
    private final VertexCalculator vertexCalculator = new VertexCalculatorImpl();

    @Override
    public final Player createPlayer(final double sideLength, final Coordinate position,
            final GraphicsComponent graphicsComponent,
            final int health, InputMovement movement) {
        movement = new InputMovementImpl();
        final List<Coordinate> vertexes = vertexCalculator.triangle(sideLength, position);
        final Player player = new Player(vertexes, position, graphicsComponent, health);
        player.setMovement(Optional.of(movement));
        return player;
    }

    @Override
    public final Enemy createEnemy(final GameEntity target, final double sideLength, final Coordinate position,
            final GraphicsComponent graphicsComponent, final int health) {
        final List<Coordinate> vertexes = vertexCalculator.triangle(sideLength, position);
        final Enemy enemy = new Enemy(vertexes, position, graphicsComponent, health);
        final Optional<Movement> movement = Optional.ofNullable(new IabfsMovement(target, enemy));
        enemy.setMovement(movement);
        return enemy;
    }

    @Override
    public final CachedGameEntity createObstacle(final double sideLength, final Coordinate position,
            final GraphicsComponent graphicsComponent) {
        final List<Coordinate> vertexes = vertexCalculator.square(sideLength, position);
        final CachedGameEntity obstacle = new Obstacle(vertexes, position, graphicsComponent);
        return obstacle;
    }

    @Override
    public final Projectile createProjectile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProjectile'");
    }

    @Override
    public final ActiveEntity createChest(final double sideLength, final Coordinate position,
            final GraphicsComponent graphicsComponent,
            final int health) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChest'");
    }

}

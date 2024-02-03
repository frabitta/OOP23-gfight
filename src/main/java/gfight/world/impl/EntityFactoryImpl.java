package gfight.world.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.EntityFactory;
import gfight.world.api.GameEntity;
import gfight.world.api.VertexCalculator;
import gfight.world.movement.api.InputMovement;
import gfight.world.movement.api.Movement;
import gfight.world.movement.impl.IabfsMovement;
import gfight.world.movement.impl.InputMovementImpl;
import gfight.world.weapon.api.Projectile;

public class EntityFactoryImpl implements EntityFactory {
    private final VertexCalculator vertexCalculator = new VertexCalculatorImpl();

    @Override
    public Player createPlayer(double sideLength, Coordinate position, GraphicsComponent graphicsComponent,
            int health, InputMovement movement) {
        movement = new InputMovementImpl();
        final List<Coordinate> vertexes = vertexCalculator.triangle(sideLength, position);
        final Player player = new Player(vertexes, position, graphicsComponent, health);
        player.setMovement(Optional.of(movement));
        return player;
    }

    @Override
    public Enemy createEnemy(GameEntity target, double sideLength, Coordinate position,
            GraphicsComponent graphicsComponent, int health) {
        final List<Coordinate> vertexes = vertexCalculator.triangle(sideLength, position);
        final Enemy enemy = new Enemy(vertexes, position, graphicsComponent, health);
        final Optional<Movement> movement = Optional.ofNullable(new IabfsMovement(target, enemy));
        enemy.setMovement(movement);
        return enemy;
    }

    @Override
    public CachedGameEntity createObstacle(double sideLength, Coordinate position,
            GraphicsComponent graphicsComponent) {
        final List<Coordinate> vertexes = vertexCalculator.square(sideLength, position);
        return null;
    }

    @Override
    public Projectile createProjectile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProjectile'");
    }

    @Override
    public ActiveEntity createChest(double sideLength, Coordinate position, GraphicsComponent graphicsComponent,
            int health) {
        final List<Coordinate> vertexes = vertexCalculator.square(sideLength, position);
        return null;
    }
}

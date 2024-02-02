package gfight.world.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.EntityFactory;
import gfight.world.api.VertexCalculator;
import gfight.world.movement.api.Movement;
import gfight.world.movement.impl.InputMovementImpl;
import gfight.world.weapon.api.Projectile;

public class EntityFactoryImpl implements EntityFactory {
    private final VertexCalculator vertexCalculator = new VertexCalculatorImpl();

    @Override
    public Player createPlayer(double sideLength, Coordinate position, GraphicsComponent graphicsComponent,
            int health) {
        final Optional<Movement> movement = Optional.ofNullable(new InputMovementImpl());
        final List<Coordinate> vertexes = vertexCalculator.triangle(sideLength, position);
        final Player player = new Player(vertexes, position, graphicsComponent, movement, health);
        return player;
    }

    @Override
    public Enemy createEnemy(double sideLength, Coordinate position, GraphicsComponent graphicsComponent, int health) {
        final Optional<Movement> movement = Optional.ofNullable(new InputMovementImpl());
        final List<Coordinate> vertexes = vertexCalculator.triangle(sideLength, position);
        final Enemy enemy = new Enemy(vertexes, position, graphicsComponent, movement, health);
        return enemy;
    }

    @Override
    public CachedGameEntity createObstacle(double sideLength, Coordinate position,
            GraphicsComponent graphicsComponent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createObstacle'");
    }

    @Override
    public Projectile createProjectile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProjectile'");
    }

    @Override
    public ActiveEntity createChest(double sideLength, Coordinate position, GraphicsComponent graphicsComponent,
            int health) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChest'");
    }
}

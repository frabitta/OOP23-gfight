package gfight.world.impl;

import java.util.List;
import java.util.Optional;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponentsFactory;
import gfight.engine.graphics.api.GraphicsComponent.EngineColor;
import gfight.engine.graphics.impl.GraphicsComponentsFactoryImpl;
import gfight.engine.graphics.impl.PolygonGraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.CachedGameEntity;
import gfight.world.api.EntityFactory;
import gfight.world.api.GameEntity;
import gfight.world.api.VertexCalculator;
import gfight.world.map.impl.Obstacle;
import gfight.world.movement.api.InputMovement;
import gfight.world.movement.api.Movement;
import gfight.world.movement.impl.BfsMovement;
import gfight.world.movement.impl.InputMovementImpl;
import gfight.world.weapon.api.Projectile;

/**
 * This class is a factory of enteties.
 */
public class EntityFactoryImpl implements EntityFactory {
    private final VertexCalculator vertexCalculator = new VertexCalculatorImpl();
    private final GraphicsComponentsFactory graphicsComponentsFactory = new GraphicsComponentsFactoryImpl();

    @Override
    public final Player createPlayer(final double sideLength, final Position2D position,
            final int health, InputMovement movement) {
        movement = new InputMovementImpl();
        final List<Position2D> vertexes = vertexCalculator.triangle(sideLength, position);
        PolygonGraphicsComponent graphicsComponent = graphicsComponentsFactory.polygon(EngineColor.YELLOW, vertexes);
        final Player player = new Player(vertexes, position, graphicsComponent, health);
        player.setMovement(Optional.of(movement));
        return player;
    }

    @Override
    public final Enemy createEnemy(final GameEntity target, final double sideLength, final Position2D position,
            final int health) {
        final List<Position2D> vertexes = vertexCalculator.triangle(sideLength, position);
        PolygonGraphicsComponent graphicsComponent = graphicsComponentsFactory.polygon(EngineColor.BLUE, vertexes);
        final Enemy enemy = new Enemy(vertexes, position, graphicsComponent, health);
        final Optional<Movement> movement = Optional.ofNullable(new BfsMovement(target, enemy));
        enemy.setMovement(movement);
        return enemy;
    }

    @Override
    public final CachedGameEntity createObstacle(final double sideLength, final Position2D position) {
        final List<Position2D> vertexes = vertexCalculator.square(sideLength, position);
        PolygonGraphicsComponent graphicsComponent = graphicsComponentsFactory.polygon(EngineColor.BLACK, vertexes);
        final CachedGameEntity obstacle = new Obstacle(vertexes, position, graphicsComponent);
        return obstacle;
    }

    @Override
    public final Projectile createProjectile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProjectile'");
    }

    @Override
    public final ActiveEntity createChest(final double sideLength, final Position2D position,
            final int health) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChest'");
    }

}

package gfight.world.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.EntityFactory;
import gfight.world.api.GameEntity;
import gfight.world.movement.api.Movement;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public GameEntity createPlayer(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent,
            Optional<Movement> movement, int health) {
        return new Player(vertexes, position, graphicsComponent, movement, health);
    }

    @Override
    public GameEntity createObstacle(List<Coordinate> vertexes, Coordinate position,
            GraphicsComponent graphicsComponent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createObstacle'");
    }

    @Override
    public GameEntity createEnemy(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent,
            Optional<Movement> movement, int health) {
        return new Enemy(vertexes, position, graphicsComponent, movement, health);
    }

    @Override
    public GameEntity createProjectile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProjectile'");
    }

}

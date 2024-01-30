package gfight.world.api;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import gfight.view.GraphicsComponent;

public interface EntityFactory {
    
    GameEntity createActiveEntity(Coordinate position, List<Coordinate> vertex, GraphicsComponent graphicsComponent, Movement movement, int health);

    GameEntity createChest(Coordinate position, List<Coordinate> vertex, Movement movement, int health);

    GameEntity createProjectile(Coordinate position, List<Coordinate> vertex, GraphicsComponent graphicsComponent, int health);
}

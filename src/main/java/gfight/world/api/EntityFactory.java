package gfight.world.api;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.impl.Enemy;
import gfight.world.impl.Player;
import gfight.world.movement.api.Movement;
import gfight.world.weapon.api.Projectile;

public interface EntityFactory {
    /**
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     * @param movement
     * @param health
     * @return a new Player
     */
    Player createPlayer(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent,
            Optional<Movement> movement, int health);
    /**
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     * @return a new Obstacle
     */
    CachedGameEntity createObstacle(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent);

    /**
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     * @param movement
     * @param health
     * @return a new Enemy
     */
    Enemy createEnemy(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent,
            Optional<Movement> movement, int health);
    /**
     * 
     * @return a new Projectile
     */
    Projectile createProjectile();
}

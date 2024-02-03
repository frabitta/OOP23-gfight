package gfight.world.api;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.impl.Enemy;
import gfight.world.impl.Player;
import gfight.world.movement.api.InputMovement;
import gfight.world.weapon.api.Projectile;

/**
 * An interface that represents the concept of a factory of entities.
 */
public interface EntityFactory {

        /**
         * 
         * @param sideLength        of the figure
         * @param position          the center of the player
         * @param graphicsComponent color of the player
         * @param health            life point of the player
         * @param movement          movement object that will be overwritten
         * @return a new Player
         */
        Player createPlayer(double sideLength, Coordinate position, GraphicsComponent graphicsComponent, int health,
                        InputMovement movement);

        /**
         * @param target            of the enemy (chest or player)
         * @param sideLength        of the figure
         * @param position          the center of the enemy
         * @param graphicsComponent color of the enemy
         * @param health            life point of the enemy
         * @return a new Enemy
         */
        Enemy createEnemy(GameEntity target, double sideLength, Coordinate position,
                        GraphicsComponent graphicsComponent, int health);

        /**
         * 
         * @param sideLength        of the long side of the rectangle
         * @param position          the center of the obstacle
         * @param graphicsComponent color of the obstacle
         * @return a new Obstacle
         */
        CachedGameEntity createObstacle(double sideLength, Coordinate position, GraphicsComponent graphicsComponent);

        /**
         * 
         * @param sideLength        of the figure
         * @param position          the centre of the chest
         * @param graphicsComponent the color of the chest
         * @param health            life point of the chest
         * @return a new Chest object
         */
        ActiveEntity createChest(double sideLength, Coordinate position, GraphicsComponent graphicsComponent,
                        int health);

        Projectile createProjectile();
}

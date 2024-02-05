package gfight.world.entity.api;

import gfight.common.api.Position2D;
import gfight.world.entity.impl.Enemy;
import gfight.world.entity.impl.Player;
import gfight.world.movement.api.InputMovement;
import gfight.world.weapon.api.Projectile;

/**
 * An interface that represents the concept of a factory of entities.
 */
public interface EntityFactory {

        /**
         * 
         * @param sideLength of the figure
         * @param position   the center of the player
         * @param health     life point of the player
         * @param movement   movement object that will be overwritten
         * @return a new Player
         */
        Player createPlayer(double sideLength, Position2D position, int health, InputMovement movement);

        /**
         * @param target     of the enemy (chest or player)
         * @param sideLength of the figure
         * @param position   the center of the enemy
         * @param health     life point of the enemy
         * @return a new Enemy
         */
        Enemy createEnemy(GameEntity target, double sideLength, Position2D position, int health);

        /**
         * 
         * @param sideLength of the long side of the rectangle
         * @param position   the center of the obstacle
         * @return a new Obstacle
         */
        CachedGameEntity createObstacle(double sideLength, Position2D position);

        /**
         * 
         * @param sideLength of the figure
         * @param position   the centre of the chest
         * @param health     life point of the chest
         * @return a new Chest object
         */
        ActiveEntity createChest(double sideLength, Position2D position, int health);

        Projectile createProjectile();
}

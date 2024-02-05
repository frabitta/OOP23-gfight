package gfight.world.entity.api;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.world.map.api.GameMap;
import gfight.world.movement.api.InputMovement;
import gfight.world.weapon.api.Projectile;
import gfight.world.weapon.api.Team;

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
        Character createPlayer(double sideLength, Position2D position, int health, InputMovement movement);

        /**
         * @param target     of the enemy (chest or player)
         * @param sideLength of the figure
         * @param position   the center of the enemy
         * @param health     life point of the enemy
         * @param map        the map of the game
         * @return a new Enemy
         */
        Character createEnemy(GameEntity target, double sideLength, Position2D position, int health, GameMap map);

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

        /**
         * Creates a new Projectile.
         * @param team Team of the projectile
         * @param position spawning position
         * @param direction direction to face
         * @return Projectile generated
         */
        Projectile createProjectile(Team team, Position2D position, Vect direction);
}

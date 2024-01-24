package gfight.world.api.map;

import java.util.Set;

import gfight.world.api.GameEntity;

/**
 * The map where the game takes place
 */
public interface GameMap {

    /**
     * Get all the enemies on the map
     * 
     * @return a set containing the enemies on the map
     */
    Set<GameEntity> getEnemies();

    /**
     * Get the player on the map
     * 
     * @return the player entity
     */
    GameEntity getPlayer();

    /**
     * Get all the game tiles of which the map is composed
     * 
     * @return a set containing all the game tiles of the map
     */
    Set<GameTile> getGameTiles();
}

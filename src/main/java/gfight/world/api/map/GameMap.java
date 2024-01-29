package gfight.world.api.map;

import java.util.Map;
import java.util.Set;

import org.locationtech.jts.geom.Coordinate;

import gfight.world.api.GameEntity;

/**
 * The map where the game takes place.
 */
public interface GameMap {

    /**
     * Get all the enemies on the map.
     * 
     * @return a set containing the enemies on the map
     */
    Set<GameEntity> getEnemies();

    /**
     * Get the player on the map.
     * 
     * @return the player entity
     */
    GameEntity getPlayer();

    /**
     * Get all the game tiles of which the map is composed.
     * 
     * @return a set containing all the game tiles of the map
     */
    Set<GameTile> getGameTiles();

    /**
     * Get the graph of the game tiles on the map.
     * 
     * @return an immutable map which associates to every node
     *         the set of the adjacents, thus creating a graph
     */
    Map<Coordinate, Set<Coordinate>> getTilesGraph();
}

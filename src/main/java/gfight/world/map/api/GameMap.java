package gfight.world.map.api;

import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import gfight.common.api.Position2D;

/**
 * The map where the game takes place.
 */
public interface GameMap {

    /**
     * The dimension of a tile.
     */
    int TILE_DIM = 40;

    /**
     * Returns the position where the player needs to spawn.
     * 
     * @return the player spawning location
     */
    Position2D getPlayerSpawn();

    /**
     * Returns the position of the chest in the map.
     * 
     * @return the position of the chest
     */
    Position2D getChestPosition();

    /**
     * Returns the positions of the obstacles in the map.
     * 
     * @return a set of the positions of the obstacles
     */
    Set<Position2D> getObstaclesPositions();

    /**
     * Returns the positions of all the spawners of the map,
     * each associated with its type.
     * 
     * @return a map of the positions of the spawners with their type
     */
    Map<Position2D, Spawner.SpawnerType> getSpawnersPositions();

    /**
     * Returns the GameTile which contains the given position.
     * 
     * @param position the position to search the associated tile
     * @return the GameTile containing {@code position}
     * @throws IllegalStateException if the given position is not inside the map
     */
    GameTile searchTile(Position2D position);

    /**
     * Returns the graph of the game tiles on the map.
     * 
     * @return an immutable Graph of the GameTiles
     */
    Graph<GameTile, DefaultEdge> getTileGraph();
}

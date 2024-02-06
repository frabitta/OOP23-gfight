package gfight.world.map.api;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import gfight.common.api.Position2D;
import gfight.world.map.impl.Obstacle;

/**
 * The map where the game takes place.
 */
public interface GameMap {

    /**
     * Get all the game tiles of which the map is composed.
     * 
     * @return a set containing all the game tiles of the map
     */
    Set<GameTile> getGameTiles();

    /**
     * Get all the obstacles present in the map.
     * 
     * @return a set containing all the obstacles in the map
     */
    Set<Obstacle> getObstacles();

    /**
     * Get the GameTile which contains the given position.
     * 
     * @param position
     * @return the GameTile containing position
     * @throws IllegalStateException if the given position is not inside the map
     */
    GameTile searchTile(Position2D position);

    /**
     * Get the graph of the game tiles on the map.
     * 
     * @return an immutable Graph of the GameTiles
     */
    Graph<GameTile, DefaultEdge> getTileGraph();
}

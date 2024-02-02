package gfight.world.map.api;

import java.util.Set;

import org.locationtech.jts.geom.Coordinate;

import com.google.common.graph.Graph;

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
     * Get the GameTile which contains the given position.
     * 
     * @param position
     * @return the GameTile containing position
     * @throws IllegalStateException if the given position is not inside the map
     */
    GameTile searchTile(Coordinate position);

    /**
     * Get the graph of the game tiles on the map.
     * 
     * @return an immutable Graph of the GameTiles
     */
    Graph<GameTile> getTileGraph();
}

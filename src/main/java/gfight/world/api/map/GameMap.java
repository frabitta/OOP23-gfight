package gfight.world.api.map;

import java.util.Map;
import java.util.Set;

import org.locationtech.jts.geom.Coordinate;

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
     */
    GameTile searchTile(Coordinate position);

    /**
     * Get the graph of the game tiles on the map.
     * 
     * @return an immutable map which associates to every node
     *         the set of the adjacents, thus creating a graph
     */
    Map<Coordinate, Set<Coordinate>> getTilesGraph();
}

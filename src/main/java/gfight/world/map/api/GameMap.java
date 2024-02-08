package gfight.world.map.api;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import gfight.common.api.Position2D;

/**
 * The map where the game takes place.
 */
public interface GameMap {

    /**
     * Returns all the game tiles of which the map is composed.
     * 
     * @return a set containing all the game tiles of the map
     */
    Set<GameTile> getGameTiles();

    /**
     * Returns the position where the player needs to spawn.
     * 
     * @return the player spawning location
     */
    Position2D getPlayerSpawn();

    /**
     * Returns all the spawners of the map.
     * 
     * @return a set of the spawners in the map
     */
    Set<Spawner> getSpawners();

    /**
     * Returns the GameTile which contains the given position.
     * 
     * @param position
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

package gfight.world.api.map;

import org.locationtech.jts.geom.Coordinate;

/**
 * A game tile representing a portion of the map.
 */
public interface GameTile {

    /**
     * Defines whether the game tile is empty, a spawner or an obstacle (e.g. wall).
     */
    enum TileType {
        /** The tile is empty. */
        EMPTY,
        /** The tile contains an obstacle. */
        OBSTACLE,
        /** The tile is a spawner. */
        SPAWNER
    }

    /**
     * Get the type of a game tile.
     * 
     * @return the type of the game tile
     */
    TileType getType();

    /**
     * Get the position of the center of the tile.
     * 
     * @return the position of the tile
     */
    Coordinate getPosition();

    /**
     * Check if a given position is inside the game tile or not.
     * 
     * @param position the position to check
     * @return true if position is inside this game tile, false otherwise
     */
    boolean contains(Coordinate position);
}

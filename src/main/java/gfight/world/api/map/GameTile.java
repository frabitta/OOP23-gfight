package gfight.world.api.map;

import org.locationtech.jts.geom.Coordinate;

/**
 * A game tile representing a portion of the map
 */
public interface GameTile {

    /**
     * Defines whether the game tile is empty, a spawner or an obstacle (e.g. wall)
     */
    public enum TileStatus {
        EMPTY,
        OBSTACLE,
        SPAWNER
    }

    /**
     * Get the status of a game tile
     * 
     * @return the status of the game tile
     */
    TileStatus getStatus();

    /**
     * Get the position of the center of the tile
     * 
     * @return the position of the tile
     */
    Coordinate getPosition();
}

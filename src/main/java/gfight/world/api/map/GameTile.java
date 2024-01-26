package gfight.world.api.map;

import org.locationtech.jts.geom.Coordinate;

/**
 * A game tile representing a portion of the map
 */
public interface GameTile {

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

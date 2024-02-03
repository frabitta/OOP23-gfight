package gfight.world.map.api;

import org.locationtech.jts.geom.Coordinate;

import gfight.common.Position2D;

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
     * Get the dimension of the side of the tile.
     * 
     * @return the dimension of the side of the tile
     */
    double getDimension();

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
    boolean contains(Position2D position);

    /**
     * Compares the specified object with this GameTile for equality.
     * Returns {@code true} if the specified object is also a GameTile and has the
     * same properties.
     * 
     * @param obj object to be compared for equality with this GameTile
     * @return {@code true} if the specified object is equal to this GameTile
     */
    @Override
    boolean equals(Object obj);
}

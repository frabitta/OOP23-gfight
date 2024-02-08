package gfight.world.map.api;

import gfight.common.api.Position2D;

/**
 * A game tile representing a portion of the map.
 */
public interface GameTile {

    /**
     * Defines whether the game tile is empty, a chest or an obstacle.
     */
    enum TileType {
        /** The tile is empty. */
        EMPTY,
        /** The tile contains an obstacle. */
        OBSTACLE,
        /** The tile is a chest. */
        CHEST
    }

    /**
     * Gets the dimension of the side of the tile.
     * 
     * @return the dimension of the side of the tile
     */
    double getDimension();

    /**
     * Gets the type of a game tile.
     * 
     * @return the type of the game tile
     */
    TileType getType();

    /**
     * Sets the new type of the tile.
     * Usefull when you don't want enemies to cross it to reach the player.
     */
    void setType(TileType type);

    /**
     * Gets the position of the center of the tile.
     * 
     * @return the position of the tile
     */
    Position2D getPosition();

    /**
     * Checks if a given position is inside the game tile or not.
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

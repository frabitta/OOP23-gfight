package gfight.world.api.map;

/**
 * A game tile representing a portion of the map
 */
public interface GameTile {

    public enum TileStatus {
        EMPTY,
        SPAWNER
    }

    /**
     * Get the status of a game tile
     * 
     * @return the status of the game tile
     */
    TileStatus getStatus();
}

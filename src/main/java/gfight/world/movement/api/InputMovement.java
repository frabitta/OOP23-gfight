package gfight.world.movement.api;

/**
 * A particular movement for the input.
 */
public interface InputMovement extends Movement {

    /**
     * It passes a direction to add to the player.
     * 
     * @param dir direction taken from the player.
     */
    void addDirection(Directions dir);

    /**
     * It passes a direction to remove to the player.
     * 
     * @param dir dirction that the player doesn't use anymore.
     */
    void removeDirection(Directions dir);

    /**
     * It sets the input direction to (0, 0).
     */
    void setNullDirection();

    /**
     * An enum rapresenting the direction that can be added to the player.
     */
    enum Directions {
        /**
         * north direction.
         */
        NORTH,
        /**
         * south direction.
         */
        SOUTH,
        /**
         * west direction.
         */
        WEST,
        /**
         * east direction.
         */
        EAST
    }
}

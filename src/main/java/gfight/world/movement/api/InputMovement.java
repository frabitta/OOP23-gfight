package gfight.world.movement.api;

/**
 * A particular movement for the input.
 */
public interface InputMovement extends Movement {

    /**
     * It passes a direction to add to the player.
     * 
     * @param dir
     */
    void addDirection(Directions dir);

    /**
     * It passes a direction to remove to the player.
     * 
     * @param dir
     */
    void removeDirection(Directions dir);

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

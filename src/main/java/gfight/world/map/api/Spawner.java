package gfight.world.map.api;

/**
 * A spawner which spawns enemies on the map.
 */
public interface Spawner {

    /**
     * Spawns enemies on the map.
     * The spawner will spawn enemies only if {@code isEnabled()}
     * returns {@code true}.
     */
    void spawn();

    /**
     * Checks whether the spawner is enabled or not.
     * 
     * @return a boolean describing if the spawner is enabled or disabled
     */
    boolean isEnabled();

    /**
     * Enables the spawner.
     */
    void enable();

    /**
     * Disables the spawner.
     */
    void disable();
}

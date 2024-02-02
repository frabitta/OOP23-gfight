package gfight.world.map.api;

import java.util.Set;

import gfight.world.api.GameEntity;

/**
 * A spawner which spawns enemies on the map.
 */
public interface Spawner {

    /**
     * Spawn enemies on the map. The spawner will spawn enemies only if it's enabled.
     * 
     * @return a set containing the enemies just spawned,
     *         empty if the spawner is disabled
     */
    Set<GameEntity> spawnEnemies();

    /**
     * Check whether the spawner is enabled or not.
     * 
     * @return a boolean describing if the spawner is enabled or disabled
     */
    boolean isEnabled();

    /**
     * Enable the spawner.
     */
    void enable();

    /**
     * Disable the spawner.
     */
    void disable();
}

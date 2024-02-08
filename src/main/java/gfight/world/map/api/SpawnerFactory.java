package gfight.world.map.api;

import gfight.common.api.Position2D;

/**
 * A factory for creating different types of spawners.
 */
public interface SpawnerFactory {

    /**
     * Creates a new spawner which always spawns the same enemy.
     * 
     * @param position the position of the spawner
     * @return a linear spawner
     */
    Spawner createLinear(final Position2D position);

    /**
     * Creates a new spawner which spawn the same enemy
     * which strength scale with the current level.
     * 
     * @param position the position of the spawner
     * @return a scalar spawner
     */
    Spawner createScalar(final Position2D position);

    /**
     * Creates a new spawner which spawns a boss.
     * 
     * @param position the position of the spawner
     * @return a boss spawner
     */
    Spawner createBoss(final Position2D position);
}

package gfight.world.map.api;

/**
 * A factory for creating different types of spawners.
 */
public interface SpawnerFactory {

    /**
     * Creates a new spawner which always spawns the same enemy.
     * 
     * @return a linear spawner
     */
    Spawner createLinear();

    /**
     * Creates a new spawner which spawn the same enemy
     * which strength scale with the current level.
     * 
     * @return a scalar spawner
     */
    Spawner createScalar();

    /**
     * Creates a new spawner which spawns a boss.
     * 
     * @return a boss spawner
     */
    Spawner createBoss();
}

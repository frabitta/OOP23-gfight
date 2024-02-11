package gfight.world.map.api;

import java.util.Set;

import gfight.common.api.Position2D;
import gfight.world.entity.api.ActiveEntity;

/**
 * A factory for creating different types of spawners.
 */
public interface SpawnerFactory {

    /**
     * Creates a new spawner which always spawns the same enemy.
     * 
     * @param position the position of the spawner
     * @param targets  the possible targets to attack
     * @return a linear spawner
     */
    Spawner createLinear(final Position2D position, final Set<? extends ActiveEntity> targets);

    /**
     * Creates a new spawner which spawn the same enemy
     * which strength scale with the current level.
     * 
     * @param position the position of the spawner
     * @param targets  the possible targets to attack
     * @return a scalar spawner
     */
    Spawner createScalar(final Position2D position, final Set<? extends ActiveEntity> targets);

    /**
     * Creates a new spawner which spawns a boss.
     * 
     * @param position the position of the spawner
     * @param targets  the possible targets to attack
     * @return a boss spawner
     */
    Spawner createBoss(final Position2D position, final Set<? extends ActiveEntity> targets);
}

package gfight.world.api.map;

import java.util.Set;

import gfight.world.api.GameEntity;

/**
 * A spawner which spawns enemies
 */
public interface Spawner {

    Set<GameEntity> spawnEnemies();

    boolean isEnabled();

    void enable();

    void disable();
}

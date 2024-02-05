package gfight.world.api;

import java.util.Set;

import gfight.world.entity.api.CachedGameEntity;
import gfight.world.entity.api.EntityFactory;

/**
 * A decorator of an entity factory which stores every created game entity.
 */
public interface EntityManager extends EntityFactory {

    /**
     * Returns a set of all the game entities on the board.
     * 
     * @return the set of game entities
     */
    Set<CachedGameEntity> getEntities();

    /**
     * Check whether all enemies on the board are dead or still alive.
     * 
     * @return {@code true} if all enemies are dead, {@code false} otherwise
     */
    boolean isClear();
}

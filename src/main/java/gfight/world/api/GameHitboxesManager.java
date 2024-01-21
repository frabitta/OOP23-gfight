package gfight.world.api;

import java.util.Map;
import java.util.Set;

public interface GameHitboxesManager {
    /**
     * it signals to all onjects that hitboxes needs to be recalculated
     * 
     * @param gameobjects all object you want to calculate updated hitboxes
     */
    void freeHitboxes(Set<CachedGameEntity> gameobjects);

    /**
     * it crreates a graph of all the collision of gameobjects
     * 
     * @param gameObjects you want to calculate collision
     * @return Graph of the colisions
     */
    Map<CachedGameEntity, Set<CachedGameEntity>> getAllCollision(Set<CachedGameEntity> gameObjects);
}

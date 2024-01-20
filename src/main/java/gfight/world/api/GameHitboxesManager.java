package gfight.world.api;

import java.util.Map;
import java.util.Set;

public interface GameHitboxesManager {
    /**
     * it signals to all onjects that hitboxes needs to be recalculated
     * @param gaemobjects all object you want to calculate updated hitboxes
     */
    void freeHitboxes(Set<GameEntity> gaemobjects);

    /**
     * it crreates a graph of all the collision of gameobjects
     * @param gameObjects you want to calculate collision
     * @return Graph of the colisions
     */
    Map<GameEntity, Set<GameEntity>> getAllCollision(Set<GameEntity> gameObjects);
}

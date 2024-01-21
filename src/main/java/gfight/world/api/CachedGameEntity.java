package gfight.world.api;

import java.util.Set;

public interface CachedGameEntity extends GameEntity {
    /**
     * reset the memorized hitboxes
     */
    void reset();

    /**
     * 
     * @param gameObjects are all the objects to be tested
     * @return all the objects in collision with this
     */
    Set<CachedGameEntity> getAllCollided(Set<CachedGameEntity> gameObjects);
}

package gfight.world.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import gfight.world.api.CachedGameEntity;
import gfight.world.api.GameEntity;
import gfight.world.api.GameHitboxesManager;

/**
 * A class that implements Game Hitboxes Manager.
 */
public final class GameHitboxesManagerImpl implements GameHitboxesManager {

    @Override
    public void freeHitboxes(final Set<CachedGameEntity> gaemobjects) {
        gaemobjects.stream().forEach(CachedGameEntity::reset);
    }

    @Override
    public Map<GameEntity, Set<GameEntity>> getAllCollision(final Set<GameEntity> gameObjects) {
        final Map<GameEntity, Set<GameEntity>> collisionMap = new LinkedHashMap<>();
        gameObjects.stream().forEach(entity -> collisionMap.put(entity, entity.getAllCollided(gameObjects)));
        return collisionMap;
    }
}

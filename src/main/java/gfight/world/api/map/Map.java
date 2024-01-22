package gfight.world.api.map;

import java.util.Set;

import gfight.world.api.GameEntity;

/**
 * The map where the game takes place
 */
public interface Map {

    Set<GameEntity> getEnemies();

    GameEntity getPlayer();

    Set<GameTile> getGameTiles();
}

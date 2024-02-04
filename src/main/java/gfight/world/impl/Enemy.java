package gfight.world.impl;

import java.util.List;
import java.util.Set;

import gfight.common.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.collision.api.CollisionCommand;
import gfight.world.collision.impl.SlideCommand;

/**
 * Class that represents the Enemy of the game.
 */
public final class Enemy extends AbstractCharacter {

    /**
     * Constructor for enemies.
     * 
     * @param vertexes          of the shape
     * @param position          the center of the enemy
     * @param graphicsComponent the color of the enemy
     * @param health            of the enemy
     */
    public Enemy(final List<Position2D> vertexes, final Position2D position, final GraphicsComponent graphicsComponent,
            final int health) {
        super(vertexes, position, graphicsComponent, health);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void applyCollisions(final Set<GameEntity> gameobjects) {
        getAllCollided(gameobjects).stream().forEach(el -> {
            if (el instanceof GameEntity) {
                CollisionCommand coll = new SlideCommand<MovingEntity, GameEntity>(this, el);
                coll.execute();
            }
        });
    }
}

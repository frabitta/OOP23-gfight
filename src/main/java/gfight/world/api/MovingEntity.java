package gfight.world.api;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public interface MovingEntity extends CachedGameEntity {
    Vector2D getDirection();

    void setDirection(Vector2D direction);

    void move();
}

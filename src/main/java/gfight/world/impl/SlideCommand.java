package gfight.world.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.locationtech.jts.geom.Coordinate;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;

public class SlideCommand<ME extends MovingEntity, GE extends GameEntity> extends AbstractCollisionCommand<ME, GE> {

    public SlideCommand(ME outercollider, GE outercollided) {
        super(outercollider, outercollided);
    }

    @Override
    public void execute() {
        Coordinate colliderCenter = collider.getPosition();
        Coordinate collidedCenter = collided.getPosition();
        Vector2D distance = new Vector2D(collidedCenter.x - colliderCenter.x, collidedCenter.y - colliderCenter.y);
        if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
            collider.setDirection(new Vector2D(-collider.getDirection().getX(), collider.getDirection().getY()));
        } else {
            collider.setDirection(new Vector2D(collider.getDirection().getX(), -collider.getDirection().getY()));
        }
        //TODO this really needs to be test in the game
    }

}

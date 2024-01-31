package gfight.world.impl;

import java.util.Optional;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import gfight.view.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.movement.api.Movement;


public class ActiveEntityImpl extends BaseMovingEntity implements ActiveEntity{
    private int health;
    private Optional<Movement> movement;
    
    public ActiveEntityImpl(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent, Optional<Movement> movement, int health) {
        super(vertexes, position, graphicsComponent, movement);
        this.health = health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void rotate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rotate'");
    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
    }

    @Override
    protected void applyCollisions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyCollisions'");
    }
   
}

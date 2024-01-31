package gfight.world.impl;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

import gfight.view.GraphicsComponent;
import gfight.world.movement.api.Movement;

public abstract class AbstractActiveEntity extends AbstractAliveEntity{
    private Movement movement;
    
    public AbstractActiveEntity(List<Coordinate> vertexes, int health, Movement movement) {
        super(vertexes, health);
        this.movement = movement;
        
    }
    
    public void rotate(){

    }

    public void shoot(){

    }
    
    
    
}

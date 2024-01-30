package gfight.world.impl;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

import gfight.view.GraphicsComponent;
import gfight.world.api.Movement;

public abstract class AbstractActiveEntity extends AbstractAliveEntity{
    private Movement movement;
    
    public AbstractActiveEntity(List<Coordinate> vertexes, int health) {
        super(vertexes, health);
        //TODO Auto-generated constructor stub
    }
    
    public void rotate(){

    }

    public void shoot(){

    }
    
    
    
}

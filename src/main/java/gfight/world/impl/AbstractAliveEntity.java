package gfight.world.impl;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;


public abstract class AbstractAliveEntity extends CachedGameEntityImpl{
    private int health;
    
    public AbstractAliveEntity(List<Coordinate> vertexes, int health) {
        super(vertexes);
        this.health = health;
    }

    int getHealth(){
        return this.health;
    }
    
    void setHealth(int health){
        this.health =  health;
    }

}

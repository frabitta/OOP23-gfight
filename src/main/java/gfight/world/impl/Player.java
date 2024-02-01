package gfight.world.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.movement.api.Movement;

public class Player extends CharacterImpl{
    
    public Player(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent,
            Optional<Movement> movement, int health) {
        super(vertexes, position, graphicsComponent, movement, health);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void applyCollisions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyCollisions'");
    }
    
}

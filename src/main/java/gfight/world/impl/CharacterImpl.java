package gfight.world.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.Character;
import gfight.world.movement.api.Movement;

public abstract class CharacterImpl extends ActiveEntityImpl implements Character{

    public CharacterImpl(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent,
            Optional<Movement> movement, int health) {
        super(vertexes, position, graphicsComponent, movement, health);
        //TODO Auto-generated constructor stub
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

    
    
}

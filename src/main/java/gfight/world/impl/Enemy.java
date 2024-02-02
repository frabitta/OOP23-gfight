package gfight.world.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import java.util.Set;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.CachedGameEntity;
import gfight.world.movement.api.Movement;

public class Enemy extends CharacterImpl{

    public Enemy(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent,
            Optional<Movement> movement, int health) {
        super(vertexes, position, graphicsComponent, movement, health);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void applyCollisions(final Set<CachedGameEntity> gameobjects) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyCollisions'");
    }
    
}

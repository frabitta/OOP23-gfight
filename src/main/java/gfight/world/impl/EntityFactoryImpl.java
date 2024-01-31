package gfight.world.impl;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

import gfight.view.GraphicsComponent;
import gfight.world.api.EntityFactory;
import gfight.world.api.GameEntity;
import gfight.world.movement.api.Movement;

public class EntityFactoryImpl implements EntityFactory{

    @Override
    public GameEntity createActiveEntity(Coordinate position, List<Coordinate> vertex,
            GraphicsComponent graphicsComponent, Movement movement, int health) {
       return null;
    }

    @Override
    public GameEntity createChest(Coordinate position, List<Coordinate> vertex, Movement movement, int health) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChest'");
    }

    @Override
    public GameEntity createProjectile(Coordinate position, List<Coordinate> vertex,
            GraphicsComponent graphicsComponent, int health) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProjectile'");
    }

   
    
}

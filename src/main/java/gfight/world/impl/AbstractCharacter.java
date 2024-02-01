package gfight.world.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.Character;
import gfight.world.api.Hitbox;
import gfight.world.movement.api.Movement;

/**
 * Class that represents the concept of main character (Player, Enemies).
 */
public abstract class AbstractCharacter extends ActiveEntityImpl implements Character{

    public AbstractCharacter(final List<Coordinate> vertexes, final Coordinate position, final GraphicsComponent graphicsComponent,
            final Optional<Movement> movement, final int health) {
        super(vertexes, position, graphicsComponent, movement, health);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void rotate(final double theta) {
        Hitbox rotation = new HitboxImpl();
        rotation.rotate(getCoordinates(), theta);
    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
    }

    
    
}

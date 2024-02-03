package gfight.world.impl;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.api.Character;
import gfight.world.hitbox.api.Hitboxes;
import gfight.world.hitbox.impl.HitboxesImpl;

/**
 * Class that represents the concept of main character (Player, Enemies).
 */
public abstract class AbstractCharacter extends AbstractActiveEntity implements Character {

    /**
     * Constructor of abstract character.
     * 
     * @param vertexes
     * @param position
     * @param graphicsComponent
     * @param health
     */
    public AbstractCharacter(final List<Coordinate> vertexes, final Coordinate position,
            final GraphicsComponent graphicsComponent, final int health) {
        super(vertexes, position, graphicsComponent, health);
        // TODO Auto-generated constructor stub
    }

    @Override
    public final void rotate(final double theta) {
        Hitboxes rotation = new HitboxesImpl();
        rotation.rotate(getCoordinates(), theta);
    }

    @Override
    public final void shoot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
    }
}

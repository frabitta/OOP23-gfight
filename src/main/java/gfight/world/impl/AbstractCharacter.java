package gfight.world.impl;

import java.util.List;

import gfight.common.api.Position2D;
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
    public AbstractCharacter(final List<Position2D> vertexes, final Position2D position,
            final GraphicsComponent graphicsComponent, final int health) {
        super(vertexes, position, graphicsComponent, health);
        // TODO Auto-generated constructor stub
    }

    @Override
    public final void rotate(final double theta) {
        Hitboxes rotation = new HitboxesImpl();
        rotation.rotate(getPosition2Ds(), theta);
    }

    @Override
    public final void shoot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
    }
}

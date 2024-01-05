package gfight.view;

import gfight.common.Position2D;
import gfight.common.Rotation2D;

/**
 * Abstract class for a generic GraphicsComponent.
 */
public class AbstractGraphicsComponent implements GraphicsComponent {

    private EngineColor color;
    private Position2D pos;
    private Rotation2D rot;

    AbstractGraphicsComponent(final EngineColor color, final Position2D pos, final Rotation2D rot) {
        this.color = color;
        this.pos = pos;
        this.rot = rot;
    }

    @Override
    public EngineColor getColor() {
        return this.color;
    }

    @Override
    public Position2D getPosition() {
        return this.pos;
    }

    @Override
    public Rotation2D getRotation() {
        return this.rot;
    }

    @Override
    public void setColor(final EngineColor color) {
        this.color = color;
    }

    @Override
    public void setPosition(final Position2D pos) {
        this.pos = pos;
    }

    @Override
    public void setRotation(final Rotation2D rot) {
        this.rot = rot;
    }

}

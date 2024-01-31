package gfight.view;

import gfight.common.Position2D;

/**
 * Abstract class for a generic GraphicsComponent.
 */
public class AbstractGraphicsComponent implements GraphicsComponent {

    private EngineColor color;
    private Position2D pos;

    AbstractGraphicsComponent(final EngineColor color, final Position2D pos) {
        this.color = color;
        this.pos = pos;
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
    public void setColor(final EngineColor color) {
        this.color = color;
    }

    @Override
    public void setPosition(final Position2D pos) {
        this.pos = pos;
    }

}

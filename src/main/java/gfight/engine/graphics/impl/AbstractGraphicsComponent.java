package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.RenderableGraphicComponent;
import gfight.view.api.GraphicsComponentRenderer;

/**
 * Abstract class for a generic GraphicsComponent.
 */
abstract class AbstractGraphicsComponent implements GraphicsComponent, RenderableGraphicComponent {

    private final GraphicsComponentRenderer renderer;

    private EngineColor color;
    private List<Position2D> pos;

    AbstractGraphicsComponent(final EngineColor color, final List<Position2D> pos, final GraphicsComponentRenderer renderer) {
        this.color = color;
        this.pos = pos;
        this.renderer = renderer;
    }

    @Override
    public EngineColor getColor() {
        return this.color;
    }

    @Override
    public List<Position2D> getPositions() {
        return this.pos;
    }

    @Override
    public void setColor(final EngineColor color) {
        this.color = color;
    }

    @Override
    public void setPositions(final List<Position2D> pos) {
        this.pos = pos;
    }

    @Override
    public GraphicsComponentRenderer getRenderer() {
        return this.renderer;
    }

}

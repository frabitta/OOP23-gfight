package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.EngineColor;
import gfight.view.api.GraphicsComponentRenderer;

/**
 * An abstract GraphicsComponent that only needs and uses 1 position.
 */
abstract class AbstractSinglePositionGraphicsComponent extends AbstractGraphicsComponent {

    AbstractSinglePositionGraphicsComponent(
            final EngineColor color,
            final List<Position2D> pos,
            final GraphicsComponentRenderer renderer,
            final GraphicType type) {
        super(color, pos, renderer, type);
    }

    @Override
    public final void setPositions(final List<Position2D> pos) {
        super.setPositions(List.of(pos.get(0)));
    }

}

package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.EngineColor;
import gfight.view.api.GraphicsComponentRenderer;

/**
 * GraphicsComponent that describes a generic Polygon.
 */
public class PolygonGraphicsComponent extends AbstractGraphicsComponent {

    PolygonGraphicsComponent(
        final EngineColor color,
        final List<Position2D> pos,
        final GraphicsComponentRenderer renderer,
        final GraphicType type) {
        super(color, pos, renderer, type);
    }

}

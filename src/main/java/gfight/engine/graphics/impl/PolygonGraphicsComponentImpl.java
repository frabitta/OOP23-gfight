package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.EngineColor;
import gfight.engine.graphics.api.PolygonGraphicsComponent;
import gfight.view.api.GraphicsComponentRenderer;

/**
 * Implementation of a PolygonGraphicsComponent that describes a generic Polygon.
 */
public final class PolygonGraphicsComponentImpl extends AbstractGraphicsComponent implements PolygonGraphicsComponent {

    PolygonGraphicsComponentImpl(
        final EngineColor color,
        final List<Position2D> pos,
        final GraphicsComponentRenderer renderer,
        final GraphicType type) {
        super(color, pos, renderer, type);
    }

}

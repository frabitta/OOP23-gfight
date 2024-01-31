package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.Position2D;
import gfight.view.api.GraphicsComponentRenderer;

/**
 * GraphicsComponent that describes a generic Polygon.
 */
public class PolygonGraphicsComponent extends AbstractGraphicsComponent {

    PolygonGraphicsComponent(final EngineColor color, final List<Position2D> pos, final GraphicsComponentRenderer renderer) {
        super(color, pos, renderer);
    }

}

package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.Position2D;
import gfight.view.api.GraphicsComponentRenderer;

public class PolygonGraphicsComponent extends AbstractGraphicsComponent{

    PolygonGraphicsComponent(EngineColor color, List<Position2D> pos, GraphicsComponentRenderer renderer) {
        super(color, pos, renderer);
    }

}

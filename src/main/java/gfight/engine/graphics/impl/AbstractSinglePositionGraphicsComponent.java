package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.view.api.GraphicsComponentRenderer;

abstract public class AbstractSinglePositionGraphicsComponent extends AbstractGraphicsComponent{

    AbstractSinglePositionGraphicsComponent(
            final EngineColor color,
            final List<Position2D> pos,
            final GraphicsComponentRenderer renderer,
            final GraphicType type) {
        super(color, pos, renderer, type);
    }

    @Override
    public void setPositions(List<Position2D> pos) {
        if (pos.size()>1) {
            pos = pos.stream().limit(1).toList();
        }
        super.setPositions(pos);
    }

}

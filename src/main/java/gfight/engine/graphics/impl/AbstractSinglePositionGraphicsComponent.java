package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.view.api.GraphicsComponentRenderer;

abstract public class AbstractSinglePositionGraphicsComponent extends AbstractGraphicsComponent{

    AbstractSinglePositionGraphicsComponent(EngineColor color, List<Position2D> pos,
            GraphicsComponentRenderer renderer) {
        super(color, pos, renderer);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void setPositions(List<Position2D> pos) {
        if (pos.size()>1) {
            pos = pos.stream().limit(1).toList();
        }
        super.setPositions(pos);
    }

}

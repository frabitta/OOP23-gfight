package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.Position2D;
import gfight.engine.graphics.api.GraphicsComponent.EngineColor;
import gfight.view.impl.PolygonGraphicsRenderer;
import gfight.view.impl.TextGraphicsRenderer;
import gfight.engine.graphics.api.GraphicsComponentsFactory;

public class GraphicsComponentsFactoryImpl implements GraphicsComponentsFactory{

    @Override
    public PolygonGraphicsComponent polygon(EngineColor color, List<Position2D> pos) {
        return new PolygonGraphicsComponent(color, pos, new PolygonGraphicsRenderer());
    }

    @Override
    public TextGraphicsComponent text(EngineColor color, Position2D pos, int size, String text) {
        TextGraphicsComponent ret = new TextGraphicsComponent(color, List.of(pos), new TextGraphicsRenderer());
        ret.setSize(size);
        ret.setText(text);
        return ret;
    }

}

package gfight.engine.graphics.api;

import java.util.List;

import gfight.common.Position2D;
import gfight.engine.graphics.api.GraphicsComponent.EngineColor;
import gfight.engine.graphics.impl.PolygonGraphicsComponent;
import gfight.engine.graphics.impl.TextGraphicsComponent;

public interface GraphicsComponentsFactory {

    PolygonGraphicsComponent polygon(EngineColor color, List<Position2D> pos);

    TextGraphicsComponent text(EngineColor color, Position2D pos, int size, String text);
}

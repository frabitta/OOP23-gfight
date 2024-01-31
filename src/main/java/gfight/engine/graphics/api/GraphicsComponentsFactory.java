package gfight.engine.graphics.api;

import java.util.List;

import gfight.common.Position2D;
import gfight.engine.graphics.api.GraphicsComponent.EngineColor;
import gfight.engine.graphics.impl.PolygonGraphicsComponent;
import gfight.engine.graphics.impl.TextGraphicsComponent;

/**
 * A factory of GraphicsComponents.
 */
public interface GraphicsComponentsFactory {

    /**
     * Returns a new PolygonGraphicsComponent with the given parameters.
     * @param color
     * @param pos
     * @return PolygonGraphicsComponent
     */
    PolygonGraphicsComponent polygon(EngineColor color, List<Position2D> pos);

    /**
     * Returns a new TextGraphicsComponent with the given parameters.
     * @param color
     * @param pos
     * @param size
     * @param text
     * @return TextGraphicsComponent
     */
    TextGraphicsComponent text(EngineColor color, Position2D pos, int size, String text);
}

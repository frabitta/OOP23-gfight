package gfight.engine.graphics.api;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent.GraphicType;
import gfight.engine.graphics.impl.PolygonGraphicsComponent;
import gfight.engine.graphics.impl.StatusBarGraphicsComponent;
import gfight.engine.graphics.impl.TextGraphicsComponent;

/**
 * A factory of GraphicsComponents.
 */
public interface GraphicsComponentsFactory {

    /**
     * Returns a new PolygonGraphicsComponent with the given parameters.
     * @param color Color of the component
     * @param pos   Position of the component
     * @param type  GraphicType of the component
     * @return PolygonGraphicsComponent
     */
    PolygonGraphicsComponent polygon(EngineColor color, List<Position2D> pos, GraphicType type);

    /**
     * Returns a new TextGraphicsComponent with the given parameters.
     * @param color Color of the component
     * @param pos   Position of the component
     * @param size  Size of the text
     * @param text  Text displayed by the component
     * @param type  GraphicType of the component
     * @return TextGraphicsComponent
     */
    TextGraphicsComponent text(EngineColor color, Position2D pos, int size, String text, GraphicType type);

    /**
     * Returns a new StatusBarGraphicsComponent with the given parameters.
     * @param bgColor       Background color of the component
     * @param statusColor   Foreground color of the component
     * @param pos           Position of the component
     * @param base          Base lenght of the component
     * @param height        Height lenght of the component
     * @param type          GraphicType of the component
     * @return StatusBarGraphicsComponent
     */
    StatusBarGraphicsComponent statusBar(EngineColor bgColor, EngineColor statusColor, Position2D pos, int base, int height, GraphicType type);
}

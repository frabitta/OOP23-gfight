package gfight.engine.graphics.impl;

import java.util.List;

import gfight.engine.graphics.api.EngineColor;
import gfight.engine.graphics.api.GraphicsComponent.GraphicType;
import gfight.view.api.GraphicsComponentRenderer;
import gfight.view.impl.PolygonGraphicsRenderer;
import gfight.view.impl.StatusBarGraphicsRenderer;
import gfight.view.impl.TextGraphicsRenderer;
import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponentsFactory;

/**
 * Implementation of a factory of GraphicsComponents.
 */
public class GraphicsComponentsFactoryImpl implements GraphicsComponentsFactory {

    @Override
    public final PolygonGraphicsComponent polygon(final EngineColor color, final List<Position2D> pos, final GraphicType type) {
        final GraphicsComponentRenderer renderer = new PolygonGraphicsRenderer();
        final PolygonGraphicsComponent out = new PolygonGraphicsComponent(color, pos, renderer, type);
        renderer.setComponent(out);
        return out;
    }

    @Override
    public final TextGraphicsComponent text(final EngineColor color, final Position2D pos, final int size, final String text, final GraphicType type) {
        final GraphicsComponentRenderer renderer = new TextGraphicsRenderer();
        final TextGraphicsComponent out = new TextGraphicsComponent(color, List.of(pos), renderer, type);
        renderer.setComponent(out);
        out.setSize(size);
        out.setText(text);
        return out;
    }

    @Override
    public StatusBarGraphicsComponent statusBar(final EngineColor bgColor, final EngineColor statusColor, final Position2D pos, final int base, final int height, final GraphicType type) {
        final GraphicsComponentRenderer renderer = new StatusBarGraphicsRenderer();
        final StatusBarGraphicsComponent out = new StatusBarGraphicsComponent(bgColor, statusColor, List.of(pos), renderer, type);
        renderer.setComponent(out);
        out.setBase(base);
        out.setHeight(height);
        return out;
    }

}

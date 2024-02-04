package gfight.engine.graphics.impl;

import java.util.List;

import gfight.engine.graphics.api.GraphicsComponent.EngineColor;
import gfight.view.api.GraphicsComponentRenderer;
import gfight.view.impl.PolygonGraphicsRenderer;
import gfight.view.impl.TextGraphicsRenderer;
import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponentsFactory;

/**
 * Implementation of a factory of GraphicsComponents.
 */
public class GraphicsComponentsFactoryImpl implements GraphicsComponentsFactory {

    @Override
    public final PolygonGraphicsComponent polygon(final EngineColor color, final List<Position2D> pos) {
        final GraphicsComponentRenderer renderer = new PolygonGraphicsRenderer();
        final PolygonGraphicsComponent out = new PolygonGraphicsComponent(color, pos, renderer);
        renderer.setComponent(out);
        return out;
    }

    @Override
    public final TextGraphicsComponent text(final EngineColor color, final Position2D pos, final int size, final String text) {
        final GraphicsComponentRenderer renderer = new TextGraphicsRenderer();
        final TextGraphicsComponent out = new TextGraphicsComponent(color, List.of(pos), renderer);
        renderer.setComponent(out);
        out.setSize(size);
        out.setText(text);
        return out;
    }

}

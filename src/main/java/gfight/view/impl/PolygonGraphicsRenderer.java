package gfight.view.impl;

import java.awt.Graphics2D;
import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;
import gfight.engine.graphics.impl.PolygonGraphicsComponent;

/**
 * The renderer for PolygonGraphicsComponent.
 */
public final class PolygonGraphicsRenderer extends AbstractGraphicsComponentRenderer {

    @Override
    boolean isCompValid(final GraphicsComponent gComp) {
        return gComp instanceof PolygonGraphicsComponent;
    }

    @Override
    void renderComp(final Graphics2D g, final ViewableCamera camera) {
        final var gComp = getGraphicsComponent();
        final List<Position2D> pointList = camera.getScreenPositions(gComp.getPositions(),gComp.getType());

        g.fillPolygon(
            pointList.stream()
                    .mapToInt(p -> (int) Math.round(p.getX()))
                    .toArray(),
            pointList.stream()
                    .mapToInt(p -> (int) Math.round(p.getY()))
                    .toArray(),
            pointList.size());
    }

}

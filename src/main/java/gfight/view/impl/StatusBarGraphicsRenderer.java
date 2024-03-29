package gfight.view.impl;

import java.awt.Graphics2D;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewCamera;
import gfight.engine.graphics.api.StatusBarGraphicsComponent;

/**
 * Renderer for StatusBarGraphicsComponent.
 */
public final class StatusBarGraphicsRenderer extends AbstractGraphicsComponentRenderer {

    @Override
    boolean isCompValid(final GraphicsComponent gComp) {
        return gComp instanceof StatusBarGraphicsComponent;
    }

    @Override
    void renderComp(final Graphics2D g, final ViewCamera camera) {
        final StatusBarGraphicsComponent gComp = (StatusBarGraphicsComponent) getGraphicsComponent();
        final Position2D pos = camera.getScreenPosition(gComp.getPositions().get(0), gComp.getType());
        final double sizeRatio = camera.getSizeRatio();

        g.fillRect(
            (int) pos.getX(),
            (int) pos.getY(),
            (int) (gComp.getBase() * sizeRatio),
            (int) (gComp.getHeight() * sizeRatio)
        );
        g.setColor(translateEngineColor(gComp.getStatusColor()));
        g.fillRect(
            (int) pos.getX(),
            (int) pos.getY(),
            (int) (gComp.getBase() * gComp.getPercentage() * sizeRatio),
            (int) (gComp.getHeight() * sizeRatio)
        );
    }

}

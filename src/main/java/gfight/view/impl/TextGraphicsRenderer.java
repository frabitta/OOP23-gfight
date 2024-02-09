package gfight.view.impl;

import java.awt.Font;
import java.awt.Graphics2D;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewCamera;
import gfight.engine.graphics.impl.TextGraphicsComponent;

/**
 * The renderer for TextGraphicsComponent.
 */
public final class TextGraphicsRenderer extends AbstractGraphicsComponentRenderer {

    @Override
    boolean isCompValid(final GraphicsComponent gComp) {
        return gComp instanceof TextGraphicsComponent;
    }

    @Override
    void renderComp(final Graphics2D g, final ViewCamera camera) {
        final TextGraphicsComponent gComp = (TextGraphicsComponent) getGraphicsComponent();
        final Position2D printPos = camera.getScreenPosition(gComp.getPositions().get(0), gComp.getType());
        final double sizeRatio = camera.getSizeRatio();
        g.setFont(new Font("Verdana", Font.PLAIN, (int) (gComp.getSize() * sizeRatio)));
        g.drawString(gComp.getText(), Math.round(printPos.getX()), Math.round(printPos.getY()));
    }

}

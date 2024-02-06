package gfight.view.impl;

import java.awt.Graphics2D;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;
import gfight.engine.graphics.impl.StatusBarGraphicsComponent;

public class StatusBarGraphicsRenderer extends AbstractGraphicsComponentRenderer {

    @Override
    boolean isCompValid(GraphicsComponent gComp) {
        return gComp instanceof StatusBarGraphicsComponent;
    }

    @Override
    void renderComp(Graphics2D g, ViewableCamera camera) {
        final StatusBarGraphicsComponent gComp = (StatusBarGraphicsComponent) getGraphicsComponent();
        final Position2D pos = camera.getScreenPosition(gComp.getPositions().get(0));
        g.fillRect((int) pos.getX(), (int) pos.getY(), gComp.getBase(), gComp.getHeight());
        g.setColor(translateEngineColor(gComp.getStatusColor()));
        g.fillRect((int) pos.getX(), (int) pos.getY(), (int) (gComp.getBase() * gComp.getPercentage()), gComp.getHeight());
    }

}

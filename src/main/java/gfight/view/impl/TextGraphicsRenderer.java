package gfight.view.impl;

import java.awt.Font;
import java.awt.Graphics2D;

import gfight.common.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;
import gfight.engine.graphics.impl.TextGraphicsComponent;

public class TextGraphicsRenderer extends AbstractGraphicsComponentRenderer {

    @Override
    boolean isCompValid(GraphicsComponent gComp) {
        return gComp instanceof TextGraphicsComponent;
    }

    @Override
    void renderComp(Graphics2D g, ViewableCamera camera) {
        TextGraphicsComponent gComp = (TextGraphicsComponent)this.gComp;
        Position2D printPos = camera.getRelativePosition(gComp.getPositions().get(0));

        g.setFont(new Font("Verdana", Font.PLAIN, gComp.getSize()));
        g.drawString(gComp.getText(), printPos.getX(), printPos.getY());
    }

}

package gfight.view.impl;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;
import gfight.view.api.GraphicsComponentRenderer;

abstract class AbstractGraphicsComponentRenderer implements GraphicsComponentRenderer {

    GraphicsComponent gComp;

    @Override
    public void render(Graphics2D g, ViewableCamera camera) {
        g.setColor(getColorFromComponent(gComp));
        g.setStroke(new BasicStroke(4f));
        
        renderComp(g, camera);
    }

    @Override
    public void setComponent(GraphicsComponent gComp) {
        if (!isCompValid(gComp)) {
            throw new IllegalArgumentException("GraphicsComponent has to be of the supported type");
        }
        this.gComp = gComp;
    }

    private Color getColorFromComponent(final GraphicsComponent gComp) {
        Color color;
        switch (gComp.getColor()) {
            case BLUE -> color = Color.BLUE;
            case RED -> color = Color.RED;
            case BLACK -> color = Color.BLACK;
            case YELLOW -> color = Color.YELLOW;
            default -> color = Color.BLACK;
        }
        return color;
    }

    abstract boolean isCompValid(GraphicsComponent gComp);

    abstract void renderComp(Graphics2D g, ViewableCamera camera);
}

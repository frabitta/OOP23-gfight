package gfight.view.impl;

import java.awt.Graphics2D;
import java.util.Optional;
import java.awt.BasicStroke;
import java.awt.Color;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.EngineColor;
import gfight.engine.graphics.api.ViewableCamera;
import gfight.view.api.GraphicsComponentRenderer;

abstract class AbstractGraphicsComponentRenderer implements GraphicsComponentRenderer {

    private Optional<GraphicsComponent> gComp = Optional.empty();

    @Override
    public void render(final Graphics2D g, final ViewableCamera camera) {
        g.setColor(getColorFromComponent(getGraphicsComponent()));
        g.setStroke(new BasicStroke(4f));

        renderComp(g, camera);
    }

    @Override
    public final void setComponent(final GraphicsComponent gComp) {
        if (!isCompValid(gComp)) {
            throw new IllegalArgumentException("GraphicsComponent has to be of the supported type");
        }
        this.gComp = Optional.ofNullable(gComp);
    }

    private Color getColorFromComponent(final GraphicsComponent gComp) {
        return translateEngineColor(gComp.getColor());
    }

    protected Color translateEngineColor(EngineColor color) {
        var hsb = Color.RGBtoHSB(color.getR(), color.getG(), color.getB(), null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }

    GraphicsComponent getGraphicsComponent() {
        if (this.gComp.isEmpty()) {
            throw new IllegalStateException("renderer doesn't have the reference to the GraphicsComponent it has to print");
        }
        return this.gComp.get();
    }

    abstract boolean isCompValid(GraphicsComponent gComp);

    abstract void renderComp(Graphics2D g, ViewableCamera camera);
}

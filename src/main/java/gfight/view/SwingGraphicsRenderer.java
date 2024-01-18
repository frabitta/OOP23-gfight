package gfight.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Optional;

import gfight.common.Position2D;

/**
 * An implementation of the GraphicsRenderer using JSwing.
 */
public class SwingGraphicsRenderer implements GraphicsRenderer {

    private static final int FONT_SIZE = 36;

    private final Graphics2D g;

    private ViewableCamera camera;

    SwingGraphicsRenderer(final Graphics2D g, final ViewableCamera camera) {
        this.g = g;
        this.camera = camera;
    }

    @Override
    public void drawGraphicsComponent(final GraphicsComponent gComp) {
        g.setColor(getColorFromComponent(gComp));
        g.setStroke(new BasicStroke(4f));
        
        Position2D printPos = this.camera.getRelativePosition(gComp.getPosition());

        if (gComp instanceof ShapeGraphicsComponent) {
            drawShape((ShapeGraphicsComponent) gComp, printPos);
        } else if (gComp instanceof TextGraphicsComponent) {
            drawText((TextGraphicsComponent) gComp, printPos);
        }
    }

    private void drawShape(final ShapeGraphicsComponent gComp, Position2D printPos) {
        switch (gComp.getShapeType()) {
            case CIRCLE -> drawCircle(gComp, printPos);
            case RECTANGLE -> drawRectangle(gComp, printPos);
        }
    }

    private void drawText(final TextGraphicsComponent gComp, Position2D printPos) {
        g.setFont(new Font("Verdana", Font.PLAIN, FONT_SIZE));
        g.drawString(gComp.getText(), printPos.getX(), printPos.getY());
    }

    private void drawRectangle(final ShapeGraphicsComponent gComp, Position2D printPos) {
        final int w = gComp.getWidth();
        final int h = gComp.getHeight();
        g.drawRect(printPos.getX() - w / 2, printPos.getY() - h / 2, w, h);
    }

    private void drawCircle(final ShapeGraphicsComponent gComp, Position2D printPos) {
        final int w = gComp.getWidth();
        final int h = gComp.getHeight();
        g.drawOval(printPos.getX() - w / 2, printPos.getY() - h / 2, w, h);
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

}

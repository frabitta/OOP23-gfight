package gfight.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * An implementation of the GraphicsRenderer using JSwing.
 */
public class SwingGraphicsRenderer implements GraphicsRenderer {

    private static final int FONT_SIZE = 36;

    private final Graphics2D g;

    SwingGraphicsRenderer(final Graphics2D g) {
        this.g = g;
    }

    @Override
    public void drawGraphicsComponent(final GraphicsComponent gComp) {
        g.setColor(getColorFromComponent(gComp));
        g.setStroke(new BasicStroke(4f));

        if (gComp instanceof ShapeGraphicsComponent) {
            drawShape((ShapeGraphicsComponent) gComp);
        } else if (gComp instanceof TextGraphicsComponent) {
            drawText((TextGraphicsComponent) gComp);
        }
    }

    private void drawShape(final ShapeGraphicsComponent gComp) {
        switch (gComp.getShapeType()) {
            case CIRCLE -> drawCircle(gComp);
            case RECTANGLE -> drawRectangle(gComp);
        }
    }

    private void drawText(final TextGraphicsComponent gComp) {
        g.setFont(new Font("Verdana", Font.PLAIN, FONT_SIZE));
        g.drawString(gComp.getText(), gComp.getPosition().getX(), gComp.getPosition().getY());
    }

    private void drawRectangle(final ShapeGraphicsComponent gComp) {
        final int w = gComp.getWidth();
        final int h = gComp.getHeight();
        g.drawRect(gComp.getPosition().getX() - w / 2, gComp.getPosition().getY() - h / 2, w, h);
    }

    private void drawCircle(final ShapeGraphicsComponent gComp) {
        final int w = gComp.getWidth();
        final int h = gComp.getHeight();
        g.drawOval(gComp.getPosition().getX() - w / 2, gComp.getPosition().getY() - h / 2, w, h);
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

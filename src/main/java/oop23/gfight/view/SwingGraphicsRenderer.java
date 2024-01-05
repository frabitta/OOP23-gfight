package oop23.gfight.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class SwingGraphicsRenderer implements GraphicsRenderer{

    private final Graphics2D g;

    SwingGraphicsRenderer(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void drawGraphicsComponent(GraphicsComponent gComp) {
        g.setColor(getColorFromComponent(gComp));
		g.setStroke(new BasicStroke(4f));

        if (gComp instanceof ShapeGraphicsComponent) {
            drawShape((ShapeGraphicsComponent)gComp);
        } else if (gComp instanceof TextGraphicsComponent) {
            drawText((TextGraphicsComponent)gComp);
        }
    }

    private void drawShape(ShapeGraphicsComponent gComp) {
        switch (gComp.getShapeType()) {
            case Circle -> drawCircle(gComp);
            case Rectangle -> drawRectangle(gComp);
        }
    }

    private void drawText(TextGraphicsComponent gComp) {
        g.setFont(new Font("Verdana", Font.PLAIN, 36));
        g.drawString(gComp.getText(), gComp.getPosition().getX(), gComp.getPosition().getY());
    }

    private void drawRectangle(ShapeGraphicsComponent gComp) {
        int w = gComp.getWidth();
        int h = gComp.getHeight();
        g.drawRect(gComp.getPosition().getX()-w/2, gComp.getPosition().getY()-h/2, w, h);
    }

    private void drawCircle(ShapeGraphicsComponent gComp) {
        int w = gComp.getWidth();
        int h = gComp.getHeight();
        g.drawOval(gComp.getPosition().getX()-w/2, gComp.getPosition().getY()-h/2, w, h);
    }

    private Color getColorFromComponent(GraphicsComponent gComp) {
        Color color;
        switch (gComp.getColor()) {
            case Blue -> color = Color.BLUE;
            case Red -> color = Color.RED;
            case Black -> color = Color.BLACK;
            case Yellow -> color = Color.YELLOW;
            default -> color = Color.BLACK;
        }
        return color;
    }

}

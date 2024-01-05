package gfight.view;

import javax.swing.JPanel;

import gfight.common.Pair;
import gfight.view.GraphicsComponent.EngineColor;
import gfight.view.ShapeGraphicsComponent.ShapeType;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Canvas class for JSwing,
 * extends a JPanel to create a panel in which we can freely paint using a GraphicsRenderer to draw GraphicsComponents.
 */
public class Canvas extends JPanel {

    private final int centerX;
    private final int centerY;

    Canvas(final int width, final int height) {
        setSize(width, height);

        this.centerX = width / 2;
        this.centerY = height / 2;
        //this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow(); 
    }

    @Override
    public void paint(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());

        final GraphicsRenderer renderer = new SwingGraphicsRenderer(g2);

        renderer.drawGraphicsComponent(new ShapeGraphicsComponent(EngineColor.BLUE, new Pair(centerX, centerY), null, ShapeType.CIRCLE, 40, 40));
        renderer.drawGraphicsComponent(new ShapeGraphicsComponent(EngineColor.RED, new Pair(100, 100), null, ShapeType.RECTANGLE, 100, 100));
        renderer.drawGraphicsComponent(new TextGraphicsComponent(EngineColor.BLACK, new Pair(40, 60), null, "Banco di prova"));
    }
}

package gfight.view;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.List;

/**
 * Canvas class for JSwing,
 * extends a JPanel to create a panel in which we can freely paint using a GraphicsRenderer to draw GraphicsComponents.
 */
public class Canvas extends JPanel {

    //private final int centerX;
    //private final int centerY;
    private final SwingView scene;

    private final ViewableCamera camera;

    Canvas(final int width, final int height, final SwingView scene, ViewableCamera camera) {
        this.scene = scene;
        //this.centerX = width / 2;
        //this.centerY = height / 2;

        setSize(width, height);
        //this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow(); //spotbugs  Overridable method requestFocusInWindow is called from constructor, sistema --------------

        this.camera = camera;
    }

    @Override
    public void paint(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;   //Unchecked/unconfirmed cast da sistemare---------

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());

        final GraphicsRenderer renderer = new SwingGraphicsRenderer(g2,this.camera);
        final List<GraphicsComponent> gCompList = scene.getGraphicsComponents();
        gCompList.stream().forEach(comp -> renderer.drawGraphicsComponent(comp));
    }
}

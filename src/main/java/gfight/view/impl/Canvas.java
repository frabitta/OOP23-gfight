package gfight.view.impl;

import javax.swing.JPanel;

import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.RenderableGraphicComponent;
import gfight.engine.graphics.api.ViewableCamera;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.List;

/**
 * Canvas class for JSwing,
 * extends a JPanel to create a panel in which we can freely paint using a GraphicsRenderer to draw GraphicsComponents.
 */
public final class Canvas extends JPanel {
    private static final long serialVersionUID = -4058048042685678594L;

    //private final int centerX;
    //private final int centerY;
    private final transient SwingView scene;
    private final transient ViewableCamera camera;

    Canvas(final int width, final int height, final SwingView scene, final ViewableCamera camera) {
        this.scene = scene;
        this.camera = camera;
        //this.centerX = width / 2;
        //this.centerY = height / 2;

        setSize(width, height);
        //this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paint(final Graphics g) {
        if (!(g instanceof Graphics2D)) {
            throw new IllegalArgumentException("Needs Graphics2D to render correctly");
        }
        final Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());

        //final GraphicsRenderer renderer = new SwingGraphicsRenderer(g2,this.camera);
        final List<GraphicsComponent> gCompList = scene.getGraphicsComponents();
        gCompList.stream()
            .filter(comp -> comp instanceof RenderableGraphicComponent)
            .map(comp -> (RenderableGraphicComponent) comp)
            .forEach(comp -> comp.getRenderer().render(g2, this.camera));
    }
}

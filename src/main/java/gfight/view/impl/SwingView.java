package gfight.view.impl;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import gfight.engine.Engine;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;
import gfight.view.api.EngineView;

import java.util.List;

/**
 * An EngineView implementation using JSwing.
 */
public class SwingView implements EngineView {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private final Engine controller;
    private JFrame frame;
    private List<GraphicsComponent> gComponentsList;

    /**
     * Constructor of the view.
     * @param controller
     */
    public SwingView(final Engine controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(ViewableCamera camera) {
        frame = new JFrame("Geometry Fight");
        frame.setSize(WIDTH, HEIGHT);     //needs to be changed---------------
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        //frame.setResizable(false);

        final Canvas canvas = new Canvas(WIDTH, HEIGHT, this, camera);
        frame.getContentPane().add(canvas);

        frame.addWindowListener(new WindowAdapter() { //needs to be changed------------
            @Override
            public void windowClosing(final WindowEvent ev) {
                System.exit(1);
            }
            @Override
            public void windowClosed(final WindowEvent ev) {
                System.exit(1);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void render(List<GraphicsComponent> gComponentsList) {
        this.gComponentsList = gComponentsList;
        this.frame.repaint();
    }

    List<GraphicsComponent> getGraphicsComponents() {
        return gComponentsList;
    }

}

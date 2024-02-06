package gfight.view.impl;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import gfight.engine.Engine;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;
import gfight.engine.input.api.InputEventListener;
import gfight.view.api.EngineView;

import java.util.Collections;
import java.util.List;

/**
 * An EngineView implementation using JSwing.
 */
public final class SwingView implements EngineView {

    private static final int WIDTH = 816;
    private static final int HEIGHT = 839;

    private final Engine engine;
    private JFrame frame;
    private List<GraphicsComponent> gComponentsList = Collections.emptyList();

    /**
     * Constructor of the view.
     * @param engine
     */
    public SwingView(final Engine engine) {
        this.engine = engine;
    }

    @Override
    public void initialize(final ViewableCamera camera) {
        frame = new JFrame("Geometry Fight");
        frame.setSize(WIDTH, HEIGHT);     //needs to be changed---------------
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        //frame.setResizable(false);

        final Canvas canvas = new Canvas(WIDTH, HEIGHT, this, camera);
        if (engine instanceof InputEventListener) {
            final InputEventListener listener = (InputEventListener) engine;
            canvas.setInputEventListener(listener);
            canvas.setInputEventFactory(listener.getInputEventFactory());
        }

        frame.getContentPane().add(canvas);

        frame.addWindowListener(new WindowAdapter() { //needs to be changed------------
            @Override
            public void windowClosing(final WindowEvent ev) {
                System.exit(-1);
            }
            @Override
            public void windowClosed(final WindowEvent ev) {
                System.exit(-1);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.requestFocusInWindow();
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void render(final List<GraphicsComponent> gComponentsList) {
        this.gComponentsList = Collections.unmodifiableList(gComponentsList);
        this.frame.repaint();
    }

    List<GraphicsComponent> getGraphicsComponents() {
        return this.gComponentsList;
    }

}

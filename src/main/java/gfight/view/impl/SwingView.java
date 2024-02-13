package gfight.view.impl;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gfight.engine.api.Engine;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewCamera;
import gfight.engine.input.api.InputEventListener;
import gfight.engine.input.api.InputEventValue;
import gfight.view.api.EngineView;

import java.util.Collections;
import java.util.List;

/**
 * An EngineView implementation using JSwing.
 */
public final class SwingView implements EngineView {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private final Engine engine;
    private final JFrame frame;

    private final JPanel cardPanel;
    private final JPanel menuPanel;
    private final JPanel deathPanel;
    private final Canvas gamePanel;
    private final ViewCamera camera;

    private List<GraphicsComponent> gComponentsList = Collections.emptyList();
    private CardLayout cardLayout;

    /**
     * Constructor of the view.
     * @param engine engine managing the app
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "It's necessary to store and external camera to print correctly on screen")
    public SwingView(final Engine engine, final ViewCamera camera) {
        this.engine = engine;
        this.camera = camera;

        this.frame = new JFrame("Geometry Fight");
        setupFrame();

        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(this.cardLayout);
        this.frame.getContentPane().add(this.cardPanel);
        this.menuPanel = new MenuPanel(this.engine,"gioca");          //put menu JPanel-------------------
        this.deathPanel = new GameOver(this.engine,"hai perso");         //put deathScreen JPanel-------------------
        this.gamePanel = setupGamePanel(camera);
        this.cardPanel.add(this.menuPanel, Pages.MENU.getName());
        this.cardPanel.add(this.deathPanel, Pages.DEATH_SCREEN.getName());
        this.cardPanel.add(this.gamePanel, Pages.GAME.getName());

        frame.pack();
        frame.setVisible(true);
    }

    private void setupFrame() {
        this.frame.setSize(WIDTH, HEIGHT);
        this.frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent ev) {
                engine.terminate();
            }
            @Override
            public void windowClosed(final WindowEvent ev) {
                engine.terminate();
            }
        });
        this.frame.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                final InputEventListener listener = (InputEventListener) engine;
                listener.notifyInputEvent(listener.getInputEventFactory().pressedValue(InputEventValue.Value.RESET));
                gamePanel.resetPressedKeys();
            }
            
        });
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Canvas setupGamePanel(final ViewCamera camera) {
        final Canvas canvas = new Canvas(WIDTH, HEIGHT, this, camera);
        if (engine instanceof InputEventListener) {
            final InputEventListener listener = (InputEventListener) engine;
            canvas.setInputEventListener(listener);
            canvas.setInputEventFactory(listener.getInputEventFactory());
        }
        canvas.requestFocusInWindow();
        return canvas;
    }

    List<GraphicsComponent> getGraphicsComponents() {
        return this.gComponentsList;
    }
    
    @Override
    public void render(final List<GraphicsComponent> gComponentsList) {
        this.camera.setScreenDimension(frame.getSize().getWidth(), frame.getSize().getHeight());
        this.gComponentsList = Collections.unmodifiableList(gComponentsList);
        this.frame.repaint();
    }

    @Override
    public void changePage(Pages panel) {
        this.cardLayout.show(this.cardPanel, panel.getName());
        if (panel == Pages.GAME) {
            this.gamePanel.resetPressedKeys();
            this.gamePanel.requestFocusInWindow();
        }
    }

    @Override
    public void close() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public int getRefreshRate() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
    }
}

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
import gfight.engine.api.Engine.EngineStatus;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewCamera;
import gfight.engine.input.api.InputEventFactory;
import gfight.engine.input.api.InputEventListener;
import gfight.engine.input.api.InputEventProvider;
import gfight.engine.input.api.InputEventValue;
import gfight.view.api.CameraViewer;
import gfight.view.api.EngineView;

import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * An EngineView implementation using JSwing.
 */
public final class SwingView implements EngineView, InputEventProvider, CameraViewer {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private final Engine engine;
    private final JFrame frame;

    private final JPanel cardPanel;
    private final JPanel menuPanel;
    private final JPanel deathPanel;
    private final Canvas gamePanel;
    private final JPanel pausePanel;
    private final CardLayout cardLayout;
    
    private List<GraphicsComponent> gComponentsList = Collections.emptyList();
    private InputEventListener listener;
    private InputEventFactory inputEventFactory;
    private ViewCamera camera;

    /**
     * Constructor of the view.
     * 
     * @param engine engine managing the app
     * @param camera ViewCamera through wich observe the world
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "It's necessary to store and external camera to print correctly on screen")
    public SwingView(final Engine engine, final ViewCamera camera, final InputEventFactory inputEventFactory) {
        final String path = "src/main/resources/images/";
        
        this.engine = engine;
        this.camera = camera;
        this.inputEventFactory = inputEventFactory;
        if (engine instanceof InputEventListener) {
            setInputEventListener((InputEventListener) engine);
        }

        this.frame = new JFrame("Geometry Fight");
        setupFrame();

        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(this.cardLayout);
        this.frame.getContentPane().add(this.cardPanel);
        this.menuPanel = new MenuPanel(this.engine);
        this.deathPanel = new GameOver(this.engine);
        this.gamePanel = setupGamePanel(camera);
        this.pausePanel = new PausePanel(this.engine);
        this.cardPanel.add(this.menuPanel, Pages.MENU.getName());
        this.cardPanel.add(this.deathPanel, Pages.DEATH_SCREEN.getName());
        this.cardPanel.add(this.gamePanel, Pages.GAME.getName());
        this.cardPanel.add(this.pausePanel, Pages.PAUSE_SCREEN.getName());

        Image img = new ImageIcon(path + "Icon.png").getImage();
        frame.setIconImage(img);
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
            public void windowGainedFocus(final WindowEvent e) {
            }

            @Override
            public void windowLostFocus(final WindowEvent e) {
                listener.notifyInputEvent(inputEventFactory.pressedValue(InputEventValue.Value.RESET));
                gamePanel.resetPressedKeys();
                if (engine.getEngineStatus() == EngineStatus.GAME) {
                    engine.changeStatus(EngineStatus.PAUSE);
                }
            }
        });
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Canvas setupGamePanel(final ViewCamera camera) {
        final Canvas canvas = new Canvas(WIDTH, HEIGHT, this, camera);
        canvas.setInputEventListener(this.listener);
        canvas.setInputEventFactory(this.inputEventFactory);
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
    public void changePage(final Pages panel) {
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
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDisplayMode()
                .getRefreshRate();
    }

    @Override
    public void setInputEventListener(InputEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void setInputEventFactory(InputEventFactory factory) {
        this.inputEventFactory = factory;
        this.gamePanel.setInputEventFactory(factory);
    }

    @Override
    public void installCamera(final ViewCamera camera) {
        this.camera = camera;
        this.gamePanel.setCamera(camera);
    }
}

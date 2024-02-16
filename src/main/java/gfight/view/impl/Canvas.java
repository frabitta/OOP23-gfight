package gfight.view.impl;

import javax.swing.JPanel;

import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.RenderableGraphicComponent;
import gfight.engine.graphics.api.ViewCamera;
import gfight.engine.input.api.InputEventFactory;
import gfight.engine.input.api.InputEventListener;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

/**
 * Canvas class for JSwing,
 * extends a JPanel to create a panel in which we can freely paint using a GraphicsRenderer to draw GraphicsComponents.
 */
public final class Canvas extends JPanel implements KeyListener, MouseMotionListener, MouseListener {
    private static final long serialVersionUID = -4058048042685678594L;

    private final transient SwingView scene;
    /**
     * Set of currently pressed keys.
     */
    private final Set<Integer> pressedKeys = new HashSet<>();

    private transient ViewCamera camera;
    private transient Optional<InputEventListener> inputListener;
    private transient Optional<InputEventFactory> inputFactory;

    Canvas(final int width, final int height, final SwingView scene, final ViewCamera camera) {
        this.scene = scene;
        this.camera = camera;

        setSize(width, height);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
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

        final List<GraphicsComponent> gCompList = scene.getGraphicsComponents();
        gCompList.stream()
            .filter(comp -> comp instanceof RenderableGraphicComponent)
            .map(comp -> (RenderableGraphicComponent) comp)
            .forEach(comp -> comp.getRenderer().render(g2, this.camera));
        //generateBlackBars(g2, (int) camera.getHoriOffset(), (int) camera.getVertOffset());
    }

    /*
    private void generateBlackBars(final Graphics2D g2, final int horiOffset, final int vertOffset) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, this.getWidth(), vertOffset);
        g2.fillRect(0, this.getHeight() - vertOffset, this.getWidth(), vertOffset);
        g2.fillRect(0, 0, horiOffset, this.getHeight());
        g2.fillRect(this.getWidth() - horiOffset, 0, horiOffset, this.getHeight());
    }*/

    void setInputEventListener(final InputEventListener inputListener) {
        this.inputListener = Optional.ofNullable(inputListener);
    }

    void setInputEventFactory(final InputEventFactory inputFactory) {
        this.inputFactory = Optional.ofNullable(inputFactory);
    }

    void setCamera(final ViewCamera camera) {
        this.camera = camera;
    }

    private boolean isInputAvailable() {
        return this.inputListener.isPresent() && this.inputFactory.isPresent();
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        if (isInputAvailable()) {
            this.inputListener.get().notifyInputEvent(
                this.inputFactory.get().mouseDownAtPosition(
                    new Position2DImpl(e.getX(), e.getY())
                )
            );
        }
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        if (isInputAvailable()) {
            this.inputListener.get().notifyInputEvent(
                this.inputFactory.get().mouseUpAtPosition(
                    new Position2DImpl(e.getX(), e.getY())
                )
            );
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (isInputAvailable()) {
            final int key = e.getKeyCode();
            if (!pressedKeys.contains(key)) {
                pressedKeys.add(key);
                final var value = this.inputFactory.get().filterKeyValue(key);
                if (value.isPresent()) {
                    this.inputListener.get().notifyInputEvent(
                        this.inputFactory.get().pressedValue(value.get())
                    );
                }
            }
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        if (isInputAvailable()) {
            final int key = e.getKeyCode();
            pressedKeys.remove(key);
            final var value = this.inputFactory.get().filterKeyValue(key);
            if (value.isPresent()) {
                this.inputListener.get().notifyInputEvent(
                    this.inputFactory.get().releasedValue(value.get())
                );
            }
        }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (isInputAvailable()) {
            this.inputListener.get().notifyInputEvent(
                this.inputFactory.get().mouseDownAtPosition(
                    new Position2DImpl(e.getX(), e.getY())
                )
            );
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (isInputAvailable()) {
            this.inputListener.get().notifyInputEvent(
                this.inputFactory.get().mouseUpAtPosition(
                    new Position2DImpl(e.getX(), e.getY())
                )
            );
        }
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }

    /**
     * Reset the current state of input.
     */
    public void resetPressedKeys() {
        this.pressedKeys.clear();
    }
}

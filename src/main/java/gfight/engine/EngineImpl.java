package gfight.engine;

import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;

import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.Camera;
import gfight.engine.graphics.impl.CameraImpl;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventFactory;
import gfight.engine.input.api.InputEventListener;
import gfight.engine.input.impl.InputEventFactoryImpl;
import gfight.world.TestWorld;
import gfight.world.World;
import gfight.view.api.EngineView;
import gfight.view.impl.SwingView;

/**
 * Implementation of the game engine.
 */
public final class EngineImpl implements Engine, InputEventListener {

    private static final int FRAME_RATE = 60;
    private static final long FRAME_LENGHT = 1000 / FRAME_RATE;

    private EngineView view;
    private World world;

    private final Queue<InputEvent> inputQueue = new LinkedList<>();
    private final Queue<InputEvent> bufferInputQueue = new LinkedList<>();

    private boolean mutex;

    @Override
    public void initialize() {
        final Camera camera = new CameraImpl();
        camera.moveTo(new Position2DImpl(0, 0));

        world = new TestWorld();
        world.initialize();
        world.installCamera(camera);

        view = new SwingView(this);
        view.initialize(camera);
    }

    @Override
    public void mainLoop() {
        long prevFrameStartTime = System.currentTimeMillis();
        while (isAppRunning()) {
            final long frameStartTime = System.currentTimeMillis();
            final long deltaTime = frameStartTime - prevFrameStartTime;
            processInput();
            update(deltaTime);
            render();
            waitNextFrame(frameStartTime);
            prevFrameStartTime = frameStartTime;
        }
    }

    private void waitNextFrame(final long frameStartTime) {
        final long dt = System.currentTimeMillis() - frameStartTime;
        if (dt < FRAME_LENGHT) {
            try {
                Thread.sleep(FRAME_LENGHT - dt);
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }
    }

    private void render() {
        view.render(world.getGraphicsComponents());
    }

    private void update(final long deltaTime) {
        world.update(deltaTime);
    }

    private void processInput() {
        mutex = true;
        final var frameInputSequence = Collections.unmodifiableList(inputQueue.stream().toList());
        inputQueue.clear();
        mutex = false;
        for (final var event: frameInputSequence) {
            world.processInput(event);
        }
    }

    private boolean isAppRunning() {
        return true;
    }

    @Override
    public void notifyInputEvent(final InputEvent event) {
        if (!mutex) {
            if (!bufferInputQueue.isEmpty()) {
                inputQueue.addAll(bufferInputQueue);
                bufferInputQueue.clear();
            }
            inputQueue.add(event);
        } else {
            bufferInputQueue.add(event);
        }
    }

    @Override
    public InputEventFactory getInputEventFactory() {
        return new InputEventFactoryImpl();
    }

}

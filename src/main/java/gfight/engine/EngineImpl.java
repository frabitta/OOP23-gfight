package gfight.engine;

import java.util.Queue;
import java.util.LinkedList;

import gfight.common.Pair;
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

    private Queue<InputEvent> inputQueue = new LinkedList<>();

    @Override
    public void initialize() {
        final Camera camera = new CameraImpl();
        camera.moveTo(new Pair(40, 40)); //---- adjust--------------------------------

        view = new SwingView(this);
        view.initialize(camera);

        world = new TestWorld();
        world.initialize();
        world.installCamera(camera);
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
        while (!inputQueue.isEmpty()) {
            var event = inputQueue.poll();
            System.out.println(event.getType());
        }
    }

    private boolean isAppRunning() {
        return true;
    }

    @Override
    public void notifyInputEvent(InputEvent event) {
        inputQueue.add(event);
    }

    @Override
    public InputEventFactory getInputEventFactory() {
        return new InputEventFactoryImpl();
    }

}

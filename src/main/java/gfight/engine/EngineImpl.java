package gfight.engine;

import gfight.common.Pair;
import gfight.engine.graphics.api.Camera;
import gfight.engine.graphics.impl.CameraImpl;
import gfight.world.TestWorld;
import gfight.world.World;
import gfight.view.api.EngineView;
import gfight.view.impl.SwingView;

/**
 * Implementation of the game engine.
 */
public class EngineImpl implements Engine {

    private static final int FRAME_RATE = 60;
    private static final long FRAME_LENGHT = 1000 / FRAME_RATE;

    private EngineView view;
    private World world;

    @Override
    public void initialize() {
        Camera camera = new CameraImpl();
        camera.moveTo(new Pair(40, 40));

        view = new SwingView(this);
        view.initialize(camera);

        world = new TestWorld();
        world.instantiate();
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

    /*Needs to be changed--------------------- */
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
    }

    private boolean isAppRunning() {
        return true;
    }

}

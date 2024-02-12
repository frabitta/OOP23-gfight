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
import gfight.world.World;
import gfight.world.WorldImpl;
import gfight.view.api.EngineView;
import gfight.view.impl.SwingView;

/**
 * Implementation of the game engine.
 */
public final class EngineImpl implements Engine, InputEventListener {

    private static final int FRAME_RATE = 60;
    private static final long FRAME_LENGHT = 1000 / FRAME_RATE;
    
    private final Queue<InputEvent> inputQueue = new LinkedList<>();
    private final Queue<InputEvent> bufferInputQueue = new LinkedList<>();
    
    private EngineView view;
    private World world;
    private Status appStatus;
    private Camera camera;

    private boolean mutex;

    @Override
    public void initialize() {
        this.appStatus = Status.GAME;
        this.camera = new CameraImpl();
        camera.moveTo(new Position2DImpl(0, 0));
        
        view = new SwingView(this,camera);
    }

    @Override
    public void mainLoop() {
        while (isAppRunning()) {
            //menu ------------------
            gameLoop();
        }
    }

    private void gameLoop() {
        long prevFrameStartTime = System.currentTimeMillis();
        
        //setup game-----------------
        this.camera.moveTo(new Position2DImpl(0, 0));
        world = new WorldImpl("map1");
        world.installCamera(this.camera);

        while (isAppRunning() && !this.world.isOver()) {
            final long frameStartTime = System.currentTimeMillis();
            final long deltaTime = frameStartTime - prevFrameStartTime;
            processInput();
            update(deltaTime);
            render();
            waitNextFrame(frameStartTime);
            prevFrameStartTime = frameStartTime;
            //pause menu is inside this loop just doesn't update world.
            // if we want to exit from thhe game directly to the menu we have to add a condition on the loop and skip the death screen.
        }

        // display death screen------------
    }

    private void waitNextFrame(final long frameStartTime) {
        final long dt = System.currentTimeMillis() - frameStartTime;
        if (dt < FRAME_LENGHT) {
            try {
                Thread.sleep(FRAME_LENGHT - dt);
            } catch (InterruptedException e) {
                terminate();
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
        return this.appStatus != Status.TERMINATED;
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

    @Override
    public void terminate() {
        this.appStatus = Status.TERMINATED;
    }

}

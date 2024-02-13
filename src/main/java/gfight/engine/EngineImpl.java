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
    private EngineStatus appStatus;
    private Camera camera;

    private boolean mutex;
    private String level = "map3";

    @Override
    public void initialize() {
        this.appStatus = EngineStatus.MENU;
        this.camera = new CameraImpl();
        camera.moveTo(new Position2DImpl(0, 0));
        
        view = new SwingView(this,camera);
    }

    @Override
    public void mainLoop() {
        while (isAppRunning()) {
            switch (this.appStatus) {
                case MENU -> holdPageUntilNotified(EngineView.Pages.MENU);
                case GAME -> gameLoop();
                default -> {break;}
            }
        }
        this.view.close();
    }

    private void gameLoop() {
        long prevFrameStartTime = System.currentTimeMillis();
        
        this.camera.moveTo(new Position2DImpl(0, 0));
        this.world = new WorldImpl(this.level);
        this.world.installCamera(this.camera);

        changeVisualizedPage(EngineView.Pages.GAME);

        while (isGameRunning()) {
            final long frameStartTime = System.currentTimeMillis();
            final long deltaTime = frameStartTime - prevFrameStartTime;
            processInput();
            update(deltaTime);
            render();
            waitNextFrame(frameStartTime);
            prevFrameStartTime = frameStartTime;
            //pause menu is inside this loop just doesn't update world.
            // if we want to exit from thhe game directly to the menu we have to add a condition on the loop and skip the death screen.
            if (this.world.isOver()) {
                this.appStatus = EngineStatus.DEATH_SCREEN;
            }
        }

        if (this.appStatus == EngineStatus.DEATH_SCREEN)  {
            holdPageUntilNotified(EngineView.Pages.DEATH_SCREEN);
        }
        this.appStatus = EngineStatus.MENU;
    }

    private synchronized void holdPageUntilNotified(EngineView.Pages page) {
        changeVisualizedPage(page);
        try {
            this.wait();
        } catch (InterruptedException e) {
            terminate();
        }
    }

    private void changeVisualizedPage(EngineView.Pages page) {
        this.view.changePage(page);
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
        return this.appStatus != EngineStatus.TERMINATED;
    }

    private boolean isGameRunning() {
        return isAppRunning() && this.appStatus == EngineStatus.GAME;
    }

    @Override
    public void notifyInputEvent(final InputEvent event) {
        if (this.appStatus == EngineStatus.GAME) {
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
    }

    @Override
    public InputEventFactory getInputEventFactory() {
        return new InputEventFactoryImpl();
    }

    @Override
    public void terminate() {
        changeStatus(EngineStatus.TERMINATED);
    }

    @Override
    public synchronized void changeStatus(final EngineStatus status) {
        this.appStatus = status;
        notify();
    }

    @Override
    public void selectLevel(String level) {
        this.level = level;
    }

}

package gfight.engine.impl;

import java.util.Queue;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import gfight.App;
import gfight.common.impl.Position2DImpl;
import gfight.engine.api.Engine;
import gfight.engine.graphics.api.Camera;
import gfight.engine.graphics.impl.CameraImpl;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventFactory;
import gfight.engine.input.api.InputEventListener;
import gfight.engine.input.api.InputEventValue;
import gfight.engine.input.impl.InputEventFactoryImpl;
import gfight.world.api.World;
import gfight.world.impl.WorldImpl;
import gfight.view.api.EngineView;
import gfight.view.impl.SwingView;

/**
 * Implementation of the game engine.
 */
public final class EngineImpl implements Engine, InputEventListener {

    private static final int MILLIS = 1000;

    private long frameLenght;

    private final Queue<InputEvent> inputQueue = new LinkedList<>();
    private final Queue<InputEvent> bufferInputQueue = new LinkedList<>();

    private EngineView view;
    private World world;
    private EngineStatus engineStatus;
    private Camera camera;

    private boolean mutex;
    private String level = "map1";

    @Override
    public void initialize() {
        this.engineStatus = EngineStatus.MENU;
        this.camera = new CameraImpl();
        camera.moveTo(new Position2DImpl(0, 0));
        view = new SwingView(this, camera);
    }

    @Override
    public void mainLoop() {
        while (isAppRunning()) {
            switch (this.engineStatus) {
                case MENU -> holdPageUntilNotified(EngineView.Pages.MENU);
                case GAME -> gameLoop();
                default -> {
                }
            }
        }
        this.view.close();
    }

    private void gameLoop() {
        long prevFrameStartTime = System.currentTimeMillis();

        final int frameRate = this.view.getRefreshRate();
        this.frameLenght = MILLIS / frameRate;
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
            if (this.world.isOver()) {
                saveStats();
                this.engineStatus = EngineStatus.DEATH_SCREEN;
            }
            if (this.engineStatus == EngineStatus.PAUSE) {
                holdPageUntilNotified(EngineView.Pages.PAUSE_SCREEN);
                changeVisualizedPage(EngineView.Pages.GAME);
                prevFrameStartTime = System.currentTimeMillis();
            }
        }

        if (this.engineStatus == EngineStatus.DEATH_SCREEN) {
            holdPageUntilNotified(EngineView.Pages.DEATH_SCREEN);
        }
        this.engineStatus = EngineStatus.MENU;
    }

    private void saveStats() {
        final File dir = new File(App.GAME_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(App.GAME_FOLDER + "stats.txt", true))) {
            final int waves = this.world.getSurvivedWaves();
            final Duration time = this.world.getPlayedTime();
            bw.write("• " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
                    + "   Waves cleared: " + waves
                    + "   Time: " + time.toMinutesPart()
                    + "m " + time.toSecondsPart() + "s");
            bw.newLine();
        } catch (final IOException e) {
        }
    }

    @SuppressFBWarnings(value = "WA_NOT_IN_LOOP", justification = "We don't want to go back waiting."
            + "Once freed the thread needs to be able to proceed and exit this method.")
    private synchronized void holdPageUntilNotified(final EngineView.Pages page) {
        changeVisualizedPage(page);
        try {
            this.wait();
        } catch (InterruptedException e) {
            terminate();
        }
    }

    private void changeVisualizedPage(final EngineView.Pages page) {
        this.view.changePage(page);
    }

    private void waitNextFrame(final long frameStartTime) {
        final long dt = System.currentTimeMillis() - frameStartTime;
        if (dt < this.frameLenght) {
            try {
                Thread.sleep(this.frameLenght - dt);
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
        for (final var event : frameInputSequence) {
            if (event instanceof InputEventValue
                    && ((InputEventValue) event).getValue() == InputEventValue.Value.PAUSE) {
                this.engineStatus = EngineStatus.PAUSE;
            } else {
                world.processInput(event);
            }
        }
    }

    private boolean isAppRunning() {
        return this.engineStatus != EngineStatus.TERMINATED;
    }

    private boolean isGameRunning() {
        return isAppRunning() && this.engineStatus == EngineStatus.GAME;
    }

    @Override
    public void notifyInputEvent(final InputEvent event) {
        if (this.engineStatus == EngineStatus.GAME) {
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
        this.engineStatus = status;
        notifyAll();
    }

    @Override
    public void selectLevel(final String level) {
        this.level = level;
    }

    @Override
    public EngineStatus getEngineStatus() {
        return this.engineStatus;
    }

}

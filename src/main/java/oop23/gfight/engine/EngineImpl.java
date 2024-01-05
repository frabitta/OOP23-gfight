package oop23.gfight.engine;

import oop23.gfight.view.EngineView;
import oop23.gfight.view.SwingView;

public class EngineImpl implements Engine{
    
    private static final int frameRate = 60;
    private static final long frameLenght = 1000/frameRate;
    
    private EngineView view; 

    @Override
    public void initialize() {
        System.out.println("Initialized");
        view = new SwingView(this);
        view.initialize();
    }

    @Override
    public void mainLoop() {
        long prevFrameStartTime = System.currentTimeMillis();
        while (isAppRunning()) {
            long frameStartTime = System.currentTimeMillis();
			long deltaTime = frameStartTime - prevFrameStartTime;
            processInput();
            update(deltaTime);
            render();
            waitNextFrame(frameStartTime);
            prevFrameStartTime = frameStartTime;
        }
        System.out.println("App closed");
    }

    /*Needs to be changed--------------------- */
    private void waitNextFrame(long frameStartTime) {
        long dt = System.currentTimeMillis() - frameStartTime;
		if (dt < frameLenght){
			try {
				Thread.sleep(frameLenght - dt);
			} catch (Exception ex){}
		}
    }

    private void render() {
        //System.out.println(counter+"");
        view.render();
    }

    private int counter=0;
    private void update(long deltaTime) {
        counter++;
    }

    private void processInput() {
    }

    private boolean isAppRunning() {
        return true;
    }

}

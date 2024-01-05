package oop23.gfight.engine;

public class EngineImpl implements Engine{
    
    private static final int frameRate = 60;
    private final long frameLenght = 1000/frameRate;

    @Override
    public void initialize() {
        System.out.println("Initialized");
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

    private void waitNextFrame(long frameStartTime) {
        long dt = System.currentTimeMillis() - frameStartTime;
		if (dt < frameLenght){
			try {
				Thread.sleep(frameLenght - dt);
			} catch (Exception ex){}
		}
    }

    private void render() {
        System.out.println(counter+"");
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

package gfight;

import gfight.engine.Engine;
import gfight.engine.EngineImpl;

/**
 * Main class, here the program starts creating a new engine and giving him control.
 */
final class App {

    private App() {
    }

    /**
     * main method of the program.
     * @param args
     */
    public static void main(final String[] args) {
        final Engine engine = new EngineImpl();
        engine.initialize();
        engine.mainLoop();
    }
}

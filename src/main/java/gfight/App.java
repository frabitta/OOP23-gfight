package gfight;

import gfight.engine.api.Engine;
import gfight.engine.impl.EngineImpl;

/**
 * Main class, here the program starts creating a new engine and giving him
 * control.
 */
public final class App {

    /**
     * The path containing files used by the game.
     */
    public static final String GAME_FOLDER = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + ".gfight"
            + System.getProperty("file.separator");

    private App() {
    }

    /**
     * main method of the program.
     * 
     * @param args the arguments passed from terminal
     */
    public static void main(final String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        final Engine engine = new EngineImpl();
        engine.initialize();
        engine.mainLoop();
    }
}

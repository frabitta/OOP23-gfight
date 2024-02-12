package gfight.engine;

/**
 * Interface to describe a game engine.
 */
public interface Engine {

    enum Status {
        MENU,
        GAME,
        DEATH_SCREEN,
        TERMINATED
    }

    /**
     * initializes the engine.
     */
    void initialize();

    /**
     * starts the main loop of the application.
     */
    void mainLoop();

    /**
     * terminate engine.
     */
    void terminate();
}

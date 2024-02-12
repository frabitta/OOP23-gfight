package gfight.engine;

/**
 * Interface to describe a game engine.
 */
public interface Engine {

    enum EngineStatus {
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

    /**
     * Changes the status of the Engine.
     * @param status status to put
     */
    void changeStatus(EngineStatus status);
}

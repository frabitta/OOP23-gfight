package gfight.engine.api;

/**
 * Interface to describe a game engine.
 */
public interface Engine {

    /**
     * Possible status of the Engine.
     */
    enum EngineStatus {
        /**
         * In the menu.
         */
        MENU,
        /**
         * In game.
         */
        GAME,
        /**
         * In the death screen.
         */
        DEATH_SCREEN,
        /**
         * Engine terminated.
         */
        TERMINATED,
        /**
         * Game paused.
         */
        PAUSE
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

    /**
     * Gives the current EngineStatus of the engine.
     * @return the current status
     */
    EngineStatus getEngineStatus();

    /**
     * Selects the level to load during EngineStatus.GAME.
     * @param level level name
     */
    void selectLevel(String level);
}

package gfight.view.api;

import java.util.List;

import gfight.engine.graphics.api.GraphicsComponent;

/**
 * A viewer for the engine.
 */
public interface EngineView {

    /**
     * Possible pages that the View must implement, with the corrisponding name in String.
     */
    enum Pages {
        /**
         * Menu page.
         */
        MENU("menu"),
        /**
         * Game page.
         */
        GAME("game"),
        /**
         * Death screen page.
         */
        DEATH_SCREEN("death_screen"),
        /**
         * Pause screen page.
         */
        PAUSE_SCREEN("pause_screen");

        private String name;
        Pages(final String name) {
            this.name = name;
        }
        /**
         * Get the name of the Page.
         * @return name of the Page
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * Renders a frame.
     * @param gComponentsList list of GraphicsComponents to print
     */
    void render(List<GraphicsComponent> gComponentsList);

    /**
     * Changes the page to display.
     * @param panel page of the available Pages to display.
     */
    void changePage(Pages panel);

    /**
     * Closes the window.
     */
    void close();

    /**
     * Returns the refresh rate of the screen.
     * @return int refresh rate
     */
    int getRefreshRate();
}

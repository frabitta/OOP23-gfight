package gfight.view.api;

import java.util.List;

import gfight.engine.graphics.api.GraphicsComponent;

/**
 * A viewer for the engine.
 */
public interface EngineView {

    enum Pages {
        MENU("menu"),
        GAME("game"),
        DEATH_SCREEN("death_screen");

        String name;
        Pages(String name) {
            this.name = name;
        }

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
     * Changes the page to display
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

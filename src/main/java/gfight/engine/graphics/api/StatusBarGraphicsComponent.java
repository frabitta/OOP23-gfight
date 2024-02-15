package gfight.engine.graphics.api;

/**
 * GraphicsComponent that describes a StatusBar.
 */
public interface StatusBarGraphicsComponent extends GraphicsComponent {

    /**
     * Sets the foreground color of the StatusBar.
     * @param statusColor an EngineColor
     */
    void setStatusColor(EngineColor statusColor);

    /**
     * Sets the dimensions of the component on screen.
     * @param base int for the lenght of the base of the rectangle
     * @param height int for the height of the rectangle.
     */
    void setDimension(int base, int height);

    /**
     * Sets the min and max value of the contained value.
     * @param min int for the min value
     * @param max int for the max value
     */
    void setRange(int min, int max);

    /**
     * Sets the value contained by the status bar (between min and max).
     * @param i value to insert
     */
    void setStatus(int i);

    /**
     * Returs the foreground color setted.
     * @return EngineColor of the color setted
     */
    EngineColor getStatusColor();

    /**
     * Returns the lenght of the base of the component.
     * @return int of lenght
     */
    int getBase();

    /**
     * Returns the height of the component.
     * @return int of height
     */
    int getHeight();

    /**
     * Returns the max possible value of the status. 
     * @return int of the max value
     */
    int getMax();

    /**
     * Returns the min possible value of the status. 
     * @return int of the min value
     */
    int getMin();

    /**
     * Returns the current value of the status. 
     * @return int of the value
     */
    int getStatus();

    /**
     * Returns the percentage of the value contained between min-max. 
     * @return double of percentage (between 0-1)
     */
    double getPercentage();

}

package gfight.engine.graphics.api;

/**
 * GraphicsComponent that describes a text.
 */
public interface TextGraphicsComponent extends GraphicsComponent {

    /**
     * @return text to be printed.
     */
    String getText();

    /**
     * A setter for the text of the GraphicsComponent.
     * @param text  text to print on screen
     */
    void setText(String text);

    /**
     * A setter of the font size.
     * @param size size of the font
     */
    void setSize(int size);

    /**
     * A getter of the font size.
     * @return size of the font
     */
    int getSize();
}

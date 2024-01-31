package gfight.view;

import gfight.common.Position2D;

/**
 * GraphicsComponent for Text visualization.
 */
public class TextGraphicsComponent extends AbstractGraphicsComponent {

    private String text;

    /**
     * Contructor of a TextGraphicsComponent.
     * @param color
     * @param pos
     * @param rot
     * @param text
     */
    public TextGraphicsComponent(final EngineColor color, final Position2D pos, final String text) {
        super(color, pos);
        this.text = text;
    }

    /**
     * @return text to be printed.
     */
    public String getText() {
        return text;
    }

    /**
     * A setter for the text of the GraphicsComponent.
     * @param text
     */
    public void setText(final String text) {
        this.text = text;
    }

}

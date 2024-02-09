package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.EngineColor;
import gfight.view.api.GraphicsComponentRenderer;

/**
 * GraphicsComponent that describes a Text.
 */
public class TextGraphicsComponent extends AbstractSinglePositionGraphicsComponent {

    private String text = "empty";
    private int size = 10;

    TextGraphicsComponent(final EngineColor color, final List<Position2D> pos, final GraphicsComponentRenderer renderer, final GraphicType type) {
        super(color, pos, renderer, type);
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

    /**
     * A setter of the font size.
     * @param size
     */
    public void setSize(final int size) {
        this.size = size;
    }

    /**
     * A getter of the font size.
     * @return size of the font
     */
    public int getSize() {
        return this.size;
    }

}

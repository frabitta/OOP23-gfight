package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.Position2D;
import gfight.view.api.GraphicsComponentRenderer;

public class TextGraphicsComponent extends AbstractGraphicsComponent {

    private String text = "empty";
    private int size = 10;

    TextGraphicsComponent(EngineColor color, List<Position2D> pos, GraphicsComponentRenderer renderer) {
        super(color, pos, renderer);
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

    public void setSize(final int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

}

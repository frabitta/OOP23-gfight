package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.EngineColor;
import gfight.engine.graphics.api.TextGraphicsComponent;
import gfight.view.api.GraphicsComponentRenderer;

/**
 * Implementation of TextGraphicsComponent that describes a Text.
 */
public final class TextGraphicsComponentImpl extends AbstractSinglePositionGraphicsComponent implements TextGraphicsComponent {

    private String text = "empty";
    private int size = 10;

    TextGraphicsComponentImpl(
        final EngineColor color,
        final List<Position2D> pos,
        final GraphicsComponentRenderer renderer,
        final GraphicType type) {
        super(color, pos, renderer, type);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public void setSize(final int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

}

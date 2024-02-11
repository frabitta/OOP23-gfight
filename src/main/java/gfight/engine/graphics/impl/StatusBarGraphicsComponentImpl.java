package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.engine.graphics.api.EngineColor;
import gfight.engine.graphics.api.StatusBarGraphicsComponent;
import gfight.view.api.GraphicsComponentRenderer;

/**
 * Implementation of a StatusBarGraphicsComponent to describe a status bar.
 */
public final class StatusBarGraphicsComponentImpl
    extends AbstractSinglePositionGraphicsComponent
    implements StatusBarGraphicsComponent {

    private static final int MAX = 100;
    private static final int MIN = 0;

    private int max = MAX;
    private int min = MIN;
    private int base;
    private int height;
    private int status;
    private EngineColor statusColor;

    StatusBarGraphicsComponentImpl(
        final EngineColor bgColor,
        final EngineColor statusColor,
        final List<Position2D> pos,
        final GraphicsComponentRenderer renderer,
        final GraphicType type) {
        super(bgColor, pos, renderer, type);
        this.statusColor = statusColor;
    }

    @Override
    public void setBase(final int base) {
        this.base = base;
    }

    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public void setStatus(final int i) {
        if (i > this.max) {
            this.status = this.max;
        } else if (i < this.min) {
            this.status = this.min;
        } else {
            this.status = i;
        }
    }

    @Override
    public void setStatusColor(final EngineColor statusColor) {
        this.statusColor = statusColor;
    }

    @Override
    public int getBase() {
        return this.base;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public EngineColor getStatusColor() {
        return statusColor;
    }

    @Override
    public double getPercentage() {
        return (double) (getStatus() - this.min) / (this.max - this.min);
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public void setMax(final int max) {
        this.max = max;
    }

    @Override
    public int getMin() {
        return min;
    }

    @Override
    public void setMin(final int min) {
        this.min = min;
    }
}

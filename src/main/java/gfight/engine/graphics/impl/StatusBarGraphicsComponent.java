package gfight.engine.graphics.impl;

import java.util.List;

import gfight.common.api.Position2D;
import gfight.view.api.GraphicsComponentRenderer;

public class StatusBarGraphicsComponent extends AbstractSinglePositionGraphicsComponent{

    private static final int MAX = 100;
    private static final int MIN = 0;

    private int base;
    private int height;
    private int status;
    private EngineColor statusColor;

    StatusBarGraphicsComponent(EngineColor bgColor, EngineColor statusColor, List<Position2D> pos, GraphicsComponentRenderer renderer) {
        super(bgColor, pos, renderer);
        this.statusColor = statusColor;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setStatus(int i) {
        if (i>=MAX) {
            this.status = MAX;
        } else if (i<=MIN) {
            this.status = MIN;
        } else {
            this.status = i;
        }
    }

    public void setStatusColor(EngineColor statusColor) {
        this.statusColor = statusColor;
    }

    public int getBase() {
        return this.base;
    }

    public int getHeight() {
        return this.height;
    }

    public int getStatus() {
        return this.status;
    }

    public EngineColor getStatusColor() {
        return statusColor;
    }

    public double getPercentage() {
        return (double) (getStatus() - MIN) / (MAX - MIN);
    }
}

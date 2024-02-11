package gfight.engine.graphics.impl;

import gfight.engine.graphics.api.EngineColor;

/**
 * Implementation of EngineColor.
 */
public final class EngineColorImpl implements EngineColor {

    private static final int MIN = 0;
    private static final int MAX = 255;

    private final int r;
    private final int g;
    private final int b;

    /**
     * Constructor of EngineColorImpl.
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public EngineColorImpl(final int r, final int g, final int b) {
        this.r = filter(r);
        this.g = filter(g);
        this.b = filter(b);
    }

    private int filter(final int i) {
        if (i < MIN) {
            return MIN;
        }
        if (i > MAX) {
            return MAX;
        }
        return i;
    }

    @Override
    public int getR() {
        return this.r;
    }

    @Override
    public int getG() {
        return this.g;
    }

    @Override
    public int getB() {
        return this.b;
    }

}

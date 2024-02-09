package gfight.engine.graphics.impl;

import gfight.engine.graphics.api.EngineColor;

public class EngineColorImpl implements EngineColor {

    private final int r;
    private final int g;
    private final int b;

    public EngineColorImpl(int r, int g, int b) {
        this.r = filter(r);
        this.g = filter(g);
        this.b = filter(b);
    }

    private int filter(int i) {
        if (i<0) {
            return 0;
        }
        if (i>255) {
            return 255;
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

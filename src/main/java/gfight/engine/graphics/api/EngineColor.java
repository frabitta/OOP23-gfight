package gfight.engine.graphics.api;

import gfight.engine.graphics.impl.EngineColorImpl;

public interface EngineColor {
    
    public static final EngineColor BLUE = new EngineColorImpl(0,0,255);
    public static final EngineColor RED = new EngineColorImpl(255,0,0);
    public static final EngineColor GREEN = new EngineColorImpl(0,255,0);
    public static final EngineColor WHITE = new EngineColorImpl(255,255,255);
    public static final EngineColor BLACK = new EngineColorImpl(0,0,0);
    public static final EngineColor YELLOW = new EngineColorImpl(255,255,0);

    int getR();

    int getG();

    int getB();
}

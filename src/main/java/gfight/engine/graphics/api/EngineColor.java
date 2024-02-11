package gfight.engine.graphics.api;

import gfight.engine.graphics.impl.EngineColorImpl;

/**
 * Interface to describe colors using RGB for the GraphicsComponents.
 */
public interface EngineColor {

    /**
     * Blue.
     */
    EngineColor BLUE = new EngineColorImpl(0, 0, 255);

    /**
     * Red.
     */
    EngineColor RED = new EngineColorImpl(255, 0, 0);

    /**
     * Green.
     */
    EngineColor GREEN = new EngineColorImpl(0, 255, 0);

    /**
     * White.
     */
    EngineColor WHITE = new EngineColorImpl(255, 255, 255);

    /**
     * Black.
     */
    EngineColor BLACK = new EngineColorImpl(0, 0, 0);

    /**
     * Yellow.
     */
    EngineColor YELLOW = new EngineColorImpl(255, 255, 0);

    /**
     * Getter of the Red value.
     * @return int 0-255 describing Red
     */
    int getR();

    /**
     * Getter of the Green value.
     * @return int 0-255 describing Green
     */
    int getG();

    /**
     * Getter of the Blue value.
     * @return int 0-255 describing Blue
     */
    int getB();
}

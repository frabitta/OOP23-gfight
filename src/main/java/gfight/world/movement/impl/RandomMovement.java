package gfight.world.movement.impl;

import java.util.Random;
import gfight.common.impl.VectorImpl;

/**
 * An implementation of Movement that gives a random movement.
 */
public final class RandomMovement extends BaseMovement {
    private int counter;
    private static final int MINVAL = 20;
    private static final int MAXVAL = 70;

    /**
     * Random Movement constructor.
     */
    public RandomMovement() {
        this.updateCounter();
    }

    private void updateCounter() {
        final Random rand = new Random();
        this.counter = rand.nextInt(MAXVAL - MINVAL) + MINVAL;
        final double x = rand.nextDouble() * 2 - 1;
        final double y = rand.nextDouble() * 2 - 1;
        if (x != 0.0 && y != 0.0) {
            setDirection(new VectorImpl(x, y).revert());
        } else {
            setDirection(new VectorImpl(0, 0));
        }
    }

    @Override
    public void update() {
        if (this.counter > 0) {
            this.counter--;
        } else {
            updateCounter();
        }
    }

}

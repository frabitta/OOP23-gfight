package gfight.world.movement.impl;

import java.util.Random;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

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
        updateCounter();
    }

    private void updateCounter() {
        final Random rand = new Random();
        counter = rand.nextInt(MAXVAL - MINVAL) + MINVAL;
        double x = rand.nextDouble() * 2 - 1;
        double y = rand.nextDouble() * 2 - 1;
        if (x != 0.0 && y != 0.0) {
            setDirection(new Vector2D(x, y).normalize());
        } else {
            setDirection(new Vector2D(0, 0));
        }
    }

    @Override
    public void update() {
        if (counter > 0) {
            counter--;
        } else {
            updateCounter();
        }
    }

}

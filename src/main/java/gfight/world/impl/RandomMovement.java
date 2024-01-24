package gfight.world.impl;

import java.util.Random;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class RandomMovement extends BaseMovement {
    private int counter;

    public RandomMovement() {
        updateCounter();
    }

    private void updateCounter() {
        Random rand = new Random();
        counter = rand.nextInt(20) + 50;
        dirVector = new Vector2D(1.0 * (rand.nextInt(3) - 1), 1.0 * (rand.nextInt(3) - 1));
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

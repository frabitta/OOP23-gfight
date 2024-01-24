package gfight.world.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class LinearMovement extends BaseMovement {
    private final double accelleration;

    public LinearMovement(Vector2D direction){
        this.dirVector = direction;
        accelleration = 1;
    }

    public LinearMovement(double accelleration, Vector2D direction){
        this.dirVector = direction;
        this.accelleration = accelleration;
    }

    @Override
    public void update() {
       this.dirVector = new Vector2D(accelleration, dirVector);
    }
    
}

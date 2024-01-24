package gfight.world.impl;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;

public class BestMovement extends BaseMovement{
    private final GameEntity target;
    private final MovingEntity mainObject;

    public BestMovement(GameEntity target, MovingEntity thisObject){
        this.target = target;
        this.mainObject = thisObject;
    }

    @Override
    public void update() {
        //TODO need an idea of the map to do a BFS;
    }
    
}

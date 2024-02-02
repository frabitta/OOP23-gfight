package gfight.world.movement.impl;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.map.impl.GameMapImpl;

import org.locationtech.jts.geom.Coordinate;

import com.google.common.graph.Traverser;

public class IabfsMovement extends BaseMovement {
    private final GameEntity target;
    private final MovingEntity agent;
    private final GameMapImpl map = new GameMapImpl(10);

    public IabfsMovement(final GameEntity target, final MovingEntity agent) {
        this.target = target;
        this.agent = agent;
    }

    @Override
    public void update() {
        Coordinate coordinateTarget = agent.getPosition();
        Traverser.forGraph(map.getTileGraph())
                .breadthFirst(map.searchTile(coordinateTarget))
                .forEach(x -> System.out.println(x));
    }

}

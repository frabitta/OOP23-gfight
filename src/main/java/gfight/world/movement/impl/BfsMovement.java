package gfight.world.movement.impl;

import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.GameTile;
import gfight.world.map.impl.GameMapImpl;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;

import com.google.common.graph.Traverser;

public class BfsMovement extends BaseMovement {
    private final GameEntity target;
    private final MovingEntity agent;
    private final GameMap map = new GameMapImpl(50);

    public BfsMovement(final GameEntity target, final MovingEntity agent) {
        this.target = target;
        this.agent = agent;
    }

    @Override
    public void update() {
        Coordinate coordinateTarget = agent.getPosition();
        Iterable<GameTile> nodes = Traverser.forGraph(map.getTileGraph())
                .breadthFirst(map.searchTile(coordinateTarget));
        
        
        
                
    }

    private List<GameTile> findPath(){
        List<GameTile> path = new ArrayList<>();
        Coordinate startNode = agent.getPosition();
        return null;
        
    }

}

package gfight.world.movement.impl;

import gfight.common.api.Position2D;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;
import gfight.world.map.api.GameTile;
import gfight.world.map.impl.GameMapImpl;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.alg.shortestpath.BFSShortestPath;


public class BfsMovement extends BaseMovement {
    private final GameEntity target;
    private final MovingEntity agent;
    private final GameMapImpl map = new GameMapImpl(10);

    public BfsMovement(final GameEntity target, final MovingEntity agent) {
        this.target = target;
        this.agent = agent;
    }

    @Override
    public void update() {

    }

    private void bfs() {
        Position2D start = agent.getPosition();

    }

}

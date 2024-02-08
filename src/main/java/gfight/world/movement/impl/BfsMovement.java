package gfight.world.movement.impl;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.common.impl.VectorImpl;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.GameTile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.alg.shortestpath.BFSShortestPath;

/**
 * Class that represents the movement of the enemies.
 */
public final class BfsMovement extends BaseMovement {
    private final GameEntity target;
    private final MovingEntity agent;
    private final GameMap map;

    /**
     * Constructor of bfs movement.
     * 
     * @param agent  the enemy
     * @param target that the enemy needs to reach (Chest of Player)
     * @param map    of the game
     */
    public BfsMovement(final MovingEntity agent, final GameEntity target, final GameMap map) {
        this.target = target;
        this.agent = agent;
        this.map = map;
    }

    @Override
    public void update() {
        List<Position2D> path = getPathFromBfs();
        if (agent.getPosition() != target.getPosition()) {
            Vect newDirection = new VectorImpl(agent.getPosition(), path.get(1)).norm();
            setDirection(newDirection);
        }else{
            setDirection(new VectorImpl(0,0));
        }
    }

    /**
     * 
     * @return the shortest path from the agent to the target
     */
    private List<Position2D> getPathFromBfs() {
        Position2D startNode = agent.getPosition();
        Position2D targetNode = target.getPosition();
        BFSShortestPath<GameTile, DefaultEdge> bfs = new BFSShortestPath<>(map.getTileGraph());
        List<Position2D> shortestPath = Optional
                .ofNullable(bfs.getPath(map.searchTile(startNode), map.searchTile(targetNode)))
                .map(path -> path.getVertexList().stream().map(GameTile::getPosition).collect(Collectors.toList()))
                .orElseThrow(NoSuchElementException::new);
        return shortestPath;

    }

}

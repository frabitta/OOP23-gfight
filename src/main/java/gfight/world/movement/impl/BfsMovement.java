package gfight.world.movement.impl;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.common.impl.VectorImpl;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.Character.CharacterType;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.GameTile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.alg.shortestpath.BFSShortestPath;

/**
 * Class that represents the movement of the enemies.
 */
public final class BfsMovement extends BaseMovement {
    private final GameEntity target;
    private final Character agent;
    private final GameMap map;
    private final double speed;
    private static final int RANGE_RUNNER = GameMap.TILE_DIM;
    private static final int TILES_SHOOTER = 5;

    /**
     * Constructor of bfs movement.
     * 
     * @param agent  the enemy
     * @param target that the enemy needs to reach (Chest of Player)
     * @param map    of the game
     * @param speed  of the agent
     */
    public BfsMovement(final Character agent, final GameEntity target, final GameMap map, final double speed) {
        this.target = target;
        this.agent = agent;
        this.map = map;
        this.speed = speed;
    }

    @Override
    public void update() {
        this.agent.pointTo(this.target.getPosition());
        List<Position2D> path = getPathFromBfs();
        if (!path.isEmpty()) {
            if (agent.getType() == CharacterType.SHOOTER) {
                handleShooterBehavior(path);
            } else if (agent.getType() == CharacterType.RUNNER) {
                handleRunnerBehavior(path);
            }
        } else {
            agent.setDirection(new VectorImpl(0, 0));
        }
    }

    private void handleShooterBehavior(final List<Position2D> path) {
        if (path.size() < TILES_SHOOTER) {
            stopAndAttack();
        } else {
            move(path);
        }
    }

    private void handleRunnerBehavior(final List<Position2D> path) {
        if (agent.getPosition().getDistance(target.getPosition()) < RANGE_RUNNER) {
            stopAndAttack();
        } else {
            move(path);
        }
    }

    private void stopAndAttack() {
        agent.setDirection(new VectorImpl(0, 0));
        agent.makeDamage();
    }

    private void move(final List<Position2D> path) {
        Vect newDirection = new VectorImpl(agent.getPosition(), path.get(1)).norm().scale(speed);
        setDirection(newDirection);
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
                .filter(path -> path.size() >= 2)
                .orElse(Collections.emptyList());
        return shortestPath;
    }
}

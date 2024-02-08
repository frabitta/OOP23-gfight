package gfight.world.map.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.guava.MutableGraphAdapter;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.world.entity.api.EntityFactory;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.GameTile;
import gfight.world.map.api.Spawner;
import gfight.world.map.api.GameTile.TileType;

/**
 * Standard implementation of a GameMap.
 */
public final class GameMapImpl implements GameMap {

    private static final int TILE_DIM = 40;

    private final Set<Spawner> spawners;
    private final Set<GameTile> tiles;
    private final List<List<GameTile>> tileList;
    private final int dimension;
    private final EntityFactory factory;
    private Optional<Graph<GameTile, DefaultEdge>> tileGraph;

    /**
     * Utility method to convert tile grid coordinates to real game position.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the real position
     */
    private Position2D realPosition(final int x, final int y) {
        return new Position2DImpl(x * TILE_DIM + TILE_DIM / 2, y * TILE_DIM + TILE_DIM / 2);
    }

    /**
     * Creates default obstacles inside the map, one for each diagonal direction.
     */
    private void defaultScatteredObstacles() {
        this.factory.createObstacle(TILE_DIM, realPosition(this.dimension / 4, this.dimension / 4));
        this.factory.createObstacle(TILE_DIM, realPosition(this.dimension / 4, 3 * this.dimension / 4));
        this.factory.createObstacle(TILE_DIM, realPosition(3 * this.dimension / 4, this.dimension / 4));
        this.factory.createObstacle(TILE_DIM, realPosition(3 * this.dimension / 4, 3 * this.dimension / 4));
    }

    /**
     * Creates a game map with the given dimension.
     * 
     * @param dimension the number of tiles a side of the map is composed by
     * @param factory   the entity factory allowing for obstacle creation
     */
    public GameMapImpl(final int dimension, final EntityFactory factory) {
        this.spawners = new HashSet<>();
        this.dimension = dimension;
        this.tiles = new HashSet<>(dimension);
        this.tileList = new ArrayList<>(dimension);
        this.factory = factory;
        for (int i = 0; i < dimension; i++) {
            this.tileList.add(i, new ArrayList<>(dimension));
        }
        this.tileGraph = Optional.empty();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == 0 || i == dimension - 1 || j == 0 || j == dimension - 1) {
                    this.factory.createObstacle(TILE_DIM, realPosition(i, j));
                    final GameTile tile = new GameTileImpl(
                            TileType.OBSTACLE,
                            realPosition(i, j),
                            TILE_DIM);
                    this.tiles.add(tile);
                    this.tileList.get((int) i).add((int) j, tile);
                } else {
                    final GameTile tile = new GameTileImpl(
                            TileType.EMPTY,
                            realPosition(i, j),
                            TILE_DIM);
                    this.tiles.add(tile);
                    this.tileList.get((int) i).add((int) j, tile);
                }
            }
        }
        this.factory.createChest(TILE_DIM, realPosition(this.dimension / 2, this.dimension / 2), 50);
        defaultScatteredObstacles();
    }

    @Override
    public Set<GameTile> getGameTiles() {
        return Collections.unmodifiableSet(this.tiles);
    }

    @Override
    public Position2D getPlayerSpawn() {
        return new Position2DImpl(realPosition(this.dimension / 2, this.dimension / 2 - 2));
    }

    @Override
    public Set<Spawner> getSpawners() {
        return Collections.unmodifiableSet(this.spawners);
    }

    @Override
    public GameTile searchTile(final Position2D position) {
        for (final var tile : this.tiles) {
            if (tile.contains(position)) {
                return tile;
            }
        }
        throw new IllegalStateException("Entity at position " + position + " is not inside the map");
    }

    @Override
    public Graph<GameTile, DefaultEdge> getTileGraph() {
        if (!this.tileGraph.isPresent()) {
            buildGraph();
        }
        return this.tileGraph.get();
    }

    private void buildGraph() {
        final MutableGraph<GameTile> g = GraphBuilder.undirected().build();
        final MutableGraphAdapter<GameTile> adapter = new MutableGraphAdapter<>(g);
        final Graph<GameTile, DefaultEdge> g2 = new DefaultUndirectedGraph<>(DefaultEdge.class);
        final var freeCondition = GameTile.TileType.EMPTY;
        for (final var tile : this.tiles) {
            g2.addVertex(tile);
        }
        // col (x)
        for (int i = 0; i < dimension; i++) {
            // row (y)
            for (int j = 0; j < dimension; j++) {
                final var tile = this.tileList.get(i).get(j);
                Objects.requireNonNull(tile);
                if (tile.getType().equals(freeCondition)) {
                    // NORTH
                    if (j > 0 && this.tileList.get(i).get(j - 1).getType().equals(freeCondition)) {
                        g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i - 1).get(j)));
                        g2.addEdge(tile, this.tileList.get(i).get(j - 1));
                    }
                    // SOUTH
                    if (j < (dimension - 1) && this.tileList.get(i).get(j + 1).getType().equals(freeCondition)) {
                        g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i + 1).get(j)));
                        g2.addEdge(tile, this.tileList.get(i).get(j + 1));
                    }
                    // WEST
                    if (i > 0 && this.tileList.get(i - 1).get(j).getType().equals(freeCondition)) {
                        g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i).get(j - 1)));
                        g2.addEdge(tile, this.tileList.get(i - 1).get(j));
                    }
                    // EAST
                    if (i < (dimension - 1) && this.tileList.get(i + 1).get(j).getType().equals(freeCondition)) {
                        g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i).get(j + 1)));
                        g2.addEdge(tile, this.tileList.get(i + 1).get(j));
                    }
                }
            }
        }
        this.tileGraph = Optional.of(g2);
    }
}

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
import gfight.world.map.api.GameTile.TileType;

/**
 * Standard implementation of a GameMap.
 */
public final class GameMapImpl implements GameMap {

    private static final int TILE_DIM = 40;

    private final Set<GameTile> tiles;
    private final List<List<GameTile>> tileList;
    private final int dimension;
    private final EntityFactory factory;
    private Optional<Graph<GameTile, DefaultEdge>> tileGraph;

    private double rescale(final double x) {
        return (x * TILE_DIM) + (TILE_DIM / 2);
    }

    /**
     * Creates a game map with the given dimension.
     * 
     * @param dimension the number of tiles a side of the map is composed by
     */
    public GameMapImpl(final int dimension, final EntityFactory factory) {
        this.dimension = dimension;
        this.tiles = new HashSet<>(TILE_DIM);
        this.tileList = new ArrayList<>(dimension);
        this.factory = factory;
        for (int i = 0; i < dimension; i++) {
            this.tileList.add(i, new ArrayList<>(dimension));
        }
        this.tileGraph = Optional.empty();
        for (double i = 0; i < dimension; i++) {
            for (double j = 0; j < dimension; j++) {
                if (i == 0 || i == dimension - 1 || j == 0 || j == dimension - 1) {
                    this.factory.createObstacle(TILE_DIM, new Position2DImpl(rescale(i), rescale(j)));
                    final GameTile tile = new GameTileImpl(
                            TileType.OBSTACLE,
                            new Position2DImpl(rescale(i), rescale(j)),
                            TILE_DIM);
                    this.tiles.add(tile);
                    this.tileList.get((int) i).add((int) j, tile);
                } else {
                    final GameTile tile = new GameTileImpl(
                            TileType.EMPTY,
                            new Position2DImpl(rescale(i), rescale(j)),
                            TILE_DIM);
                    this.tiles.add(tile);
                    this.tileList.get((int) i).add((int) j, tile);
                }
            }
        }
    }

    @Override
    public Set<GameTile> getGameTiles() {
        return Collections.unmodifiableSet(this.tiles);
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
        // col
        for (int i = 0; i < dimension; i++) {
            // row
            for (int j = 0; j < dimension; j++) {
                final var tile = this.tileList.get(i).get(j);
                Objects.requireNonNull(tile);
                if (tile.getType().equals(freeCondition)) {
                    // NORTH
                    if (i > 0 && this.tileList.get(i - 1).get(j).getType().equals(freeCondition)) {
                        g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i - 1).get(j)));
                        g2.addEdge(tile, this.tileList.get(j).get(i - 1));
                    }
                    // SOUTH
                    if (i < (dimension - 1) && this.tileList.get(i + 1).get(j).getType().equals(freeCondition)) {
                        g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i + 1).get(j)));
                        g2.addEdge(tile, this.tileList.get(j).get(i + 1));
                    }
                    // WEST
                    if (j > 0 && this.tileList.get(i).get(j - 1).getType().equals(freeCondition)) {
                        g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i).get(j - 1)));
                        g2.addEdge(tile, this.tileList.get(j - 1).get(i));
                    }
                    // EAST
                    if (j < (dimension - 1) && this.tileList.get(i).get(j + 1).getType().equals(freeCondition)) {
                        g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i).get(j + 1)));
                        g2.addEdge(tile, this.tileList.get(j + 1).get(i));
                    }
                }
            }
        }
        this.tileGraph = Optional.of(g2);
    }
}

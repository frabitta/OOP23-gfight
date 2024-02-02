package gfight.world.map.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.locationtech.jts.geom.Coordinate;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import gfight.world.map.api.GameMap;
import gfight.world.map.api.GameTile;
import gfight.world.map.api.GameTile.TileType;

/**
 * Standard implementation of a GameMap.
 */
public final class GameMapImpl implements GameMap {

    private static final int TILE_DIM = 1;
    private final Set<GameTile> tiles;
    private final List<List<GameTile>> tileList;
    private final int dimension;
    private Optional<Graph<GameTile>> tileGraph;

    /**
     * Creates a game map with the given dimension.
     * 
     * @param dimension the number of tiles a side of the map is composed by
     */
    public GameMapImpl(final int dimension) {
        this.dimension = dimension;
        this.tiles = new HashSet<>(TILE_DIM);
        this.tileList = new ArrayList<>(dimension);
        for (int i = 0; i < dimension; i++) {
            this.tileList.add(i, new ArrayList<>(dimension));
        }
        this.tileGraph = Optional.empty();
        for (double i = 0; i < dimension; i++) {
            for (double j = 0; j < dimension; j++) {
                final GameTile tile = new GameTileImpl(
                        TileType.EMPTY,
                        new Coordinate(i + (TILE_DIM / 2), j + (TILE_DIM / 2)),
                        TILE_DIM);
                this.tiles.add(tile);
                this.tileList.get((int) i).add((int) j, tile);
            }
        }
    }

    @Override
    public Set<GameTile> getGameTiles() {
        return Collections.unmodifiableSet(this.tiles);
    }

    @Override
    public GameTile searchTile(final Coordinate position) {
        for (final var tile : this.tiles) {
            if (tile.contains(position)) {
                return tile;
            }
        }
        throw new IllegalStateException("Entity at position " + position + " is not inside the map");
    }

    @Override
    public Graph<GameTile> getTileGraph() {
        if (!this.tileGraph.isPresent()) {
            buildGraph();
        }
        return this.tileGraph.get();
    }

    private void buildGraph() {
        final MutableGraph<GameTile> g = GraphBuilder.undirected().build();
        final var freeCondition = GameTile.TileType.EMPTY;
        // row
        for (int i = 0; i < dimension; i++) {
            // col
            for (int j = 0; j < dimension; j++) {
                final var tile = this.tileList.get(i).get(j);
                Objects.requireNonNull(tile);
                // NORTH
                if (i > 0 && this.tileList.get(i - 1).get(j).getType().equals(freeCondition)) {
                    g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i - 1).get(j)));
                }
                // SOUTH
                if (i < (dimension - 1) && this.tileList.get(i + 1).get(j).getType().equals(freeCondition)) {
                    g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i + 1).get(j)));
                }
                // WEST
                if (j > 0 && this.tileList.get(i).get(j - 1).getType().equals(freeCondition)) {
                    g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i).get(j - 1)));
                }
                // EAST
                if (j < (dimension - 1) && this.tileList.get(i).get(j + 1).getType().equals(freeCondition)) {
                    g.putEdge(tile, Objects.requireNonNull(this.tileList.get(i).get(j + 1)));
                }
            }
        }
        this.tileGraph = Optional.of(g);
    }
}

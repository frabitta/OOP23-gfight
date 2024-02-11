package gfight.world.map.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.world.map.api.GameMap;
import gfight.world.map.api.GameTile;
import gfight.world.map.api.Spawner;
import gfight.world.map.api.GameTile.TileType;

/**
 * Standard implementation of a GameMap.
 */
public final class GameMapImpl implements GameMap {

    private static final char WALL = 'w';
    private static final char CHEST = 'c';
    private static final char PLAYER = 'p';
    private static final char LINEAR_SPAWNER = 'l';
    private static final char SCALAR_SPAWNER = 's';
    private static final char BOSS_SPAWNER = 'b';

    private final Map<Position2D, Spawner.SpawnerType> spawnersPositions;
    private final List<List<GameTile>> tiles;
    private Position2D playerSpawn;
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
     * Creates a game map with the given dimensions.
     */
    public GameMapImpl() {
        this.spawnersPositions = new HashMap<>();
        this.tiles = new ArrayList<>();
        this.tileGraph = Optional.empty();
        loadFromFile();
    }

    @Override
    public Position2D getChestPosition() {
        return this.tiles.stream()
                .flatMap(List::stream)
                .filter(t -> t.getType() == GameTile.TileType.CHEST)
                .findFirst()
                .get()
                .getPosition();
    }

    @Override
    public Position2D getPlayerSpawn() {
        return this.playerSpawn;
    }

    public Set<Position2D> getObstaclesPositions() {
        return this.tiles.stream()
                .flatMap(List::stream)
                .filter(t -> t.getType() == GameTile.TileType.OBSTACLE)
                .map(GameTile::getPosition)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Position2D, Spawner.SpawnerType> getSpawnersPositions() {
        return Collections.unmodifiableMap(this.spawnersPositions);
    }

    @Override
    public GameTile searchTile(final Position2D position) {
        for (final var col : this.tiles) {
            for (final var tile : col) {
                if (tile.contains(position)) {
                    return tile;
                }
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
        Graph<GameTile, DefaultEdge> g = new DefaultUndirectedGraph<>(DefaultEdge.class);
        this.tiles.stream()
                .flatMap(List::stream)
                .forEach(v -> g.addVertex(v));
        for (int i = 0; i < this.tiles.size(); i++) {
            int width = this.tiles.get(i).size();
            for (int j = 0; j < width; j++) {
                GameTile tile = this.tiles.get(i).get(j);
                if (tile.getType() == GameTile.TileType.EMPTY) {
                    addEdgeIfEmpty(g, tile, i, j - 1); // WEST
                    addEdgeIfEmpty(g, tile, i, j + 1); // EAST
                    addEdgeIfEmpty(g, tile, i - 1, j); // NORTH
                    addEdgeIfEmpty(g, tile, i + 1, j); // SOUTH
                }
            }
        }

        this.tileGraph = Optional.of(g);
    }

    private void addEdgeIfEmpty(Graph<GameTile, DefaultEdge> g, GameTile tile, int x, int y) {
        if (x >= 0 && x < this.tiles.size() && y >= 0 && y < this.tiles.get(x).size() &&
                this.tiles.get(x).get(y).getType() == GameTile.TileType.EMPTY) {
            g.addEdge(tile, this.tiles.get(x).get(y));
        }
    }

    private void loadFromFile() {
        try (final BufferedReader br = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("map/map2.txt")))) {
            int row = 0;
            for (final var line : br.lines().toList()) {
                final List<GameTile> tileRow = new ArrayList<>((line.length() / 2) + 1);
                this.tiles.add(row, tileRow);
                for (int col = 0; col * 2 < line.length(); col++) {
                    final Position2D pos = realPosition(col, row);
                    final TileType type = switch (line.charAt(col * 2)) {
                        case CHEST -> TileType.CHEST;
                        case WALL -> TileType.OBSTACLE;
                        case PLAYER -> {
                            this.playerSpawn = pos;
                            yield TileType.EMPTY;
                        }
                        case LINEAR_SPAWNER -> {
                            this.spawnersPositions.put(pos, Spawner.SpawnerType.LINEAR);
                            yield TileType.EMPTY;
                        }
                        case SCALAR_SPAWNER -> {
                            this.spawnersPositions.put(pos, Spawner.SpawnerType.SCALAR);
                            yield TileType.EMPTY;
                        }
                        case BOSS_SPAWNER -> {
                            this.spawnersPositions.put(pos, Spawner.SpawnerType.BOSS);
                            yield TileType.EMPTY;
                        }
                        default -> TileType.EMPTY;
                    };
                    tileRow.add(col, new GameTileImpl(type, pos, TILE_DIM));
                }
                row++;
            }
        } catch (final IOException e) {
            throw new IllegalArgumentException("The specified map does not exist\n" + e);
        }
    }
}

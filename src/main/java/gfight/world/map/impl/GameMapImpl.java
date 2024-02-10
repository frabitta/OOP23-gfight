package gfight.world.map.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
        final Graph<GameTile, DefaultEdge> g2 = new DefaultUndirectedGraph<>(DefaultEdge.class);
        final var freeCondition = GameTile.TileType.EMPTY;
        for (final var col : this.tiles) {
            for (final var tile : col) {
                g2.addVertex(tile);
            }
        }
        final int height = this.tiles.size();
        // row
        for (int i = 0; i < this.tiles.size(); i++) {
            // column
            final int width = this.tiles.get(i).size();
            for (int j = 0; j < this.tiles.get(i).size(); j++) {
                final var tile = this.tiles.get(i).get(j);
                if (tile.getType().equals(freeCondition)) {
                    // EAST
                    if (j > 0 && this.tiles.get(i).get(j - 1).getType().equals(freeCondition)) {
                        g2.addEdge(tile, this.tiles.get(i).get(j - 1));
                    }
                    // WEST
                    if (j < (width - 1) && this.tiles.get(i).get(j + 1).getType().equals(freeCondition)) {
                        g2.addEdge(tile, this.tiles.get(i).get(j + 1));
                    }
                    // NORTH
                    if (i > 0 && this.tiles.get(i - 1).get(j).getType().equals(freeCondition)) {
                        g2.addEdge(tile, this.tiles.get(i - 1).get(j));
                    }
                    // SOUTH
                    if (i < (height - 1) && this.tiles.get(i + 1).get(j).getType().equals(freeCondition)) {
                        g2.addEdge(tile, this.tiles.get(i + 1).get(j));
                    }
                }
            }
        }
        this.tileGraph = Optional.of(g2);
    }

    private void loadFromFile() {
        try (final InputStream in = ClassLoader.getSystemResourceAsStream("map2.txt")) {
            int row = 0;
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            for (final var line : br.lines().toList()) {
                final List<GameTile> tileRow = new ArrayList<>((line.length() / 2) + 1);
                this.tiles.add(row, tileRow);
                int col = 0;
                while (col * 2 < line.length()) {
                    switch (line.charAt(col * 2)) {
                        case CHEST:
                            tileRow.add(col, new GameTileImpl(TileType.CHEST, realPosition(col, row), TILE_DIM));
                            break;
                        case WALL:
                            tileRow.add(col, new GameTileImpl(TileType.OBSTACLE, realPosition(col, row), TILE_DIM));
                            break;
                        case LINEAR_SPAWNER:
                            this.spawnersPositions.put(realPosition(col, row), Spawner.SpawnerType.LINEAR);
                            tileRow.add(col, new GameTileImpl(TileType.EMPTY, realPosition(col, row), TILE_DIM));
                            break;
                        case SCALAR_SPAWNER:
                            this.spawnersPositions.put(realPosition(col, row), Spawner.SpawnerType.SCALAR);
                            tileRow.add(col, new GameTileImpl(TileType.EMPTY, realPosition(col, row), TILE_DIM));
                            break;
                        case BOSS_SPAWNER:
                            this.spawnersPositions.put(realPosition(col, row), Spawner.SpawnerType.BOSS);
                            tileRow.add(col, new GameTileImpl(TileType.EMPTY, realPosition(col, row), TILE_DIM));
                            break;
                        case PLAYER:
                            this.playerSpawn = realPosition(col, row);
                        default:
                            tileRow.add(col, new GameTileImpl(TileType.EMPTY, realPosition(col, row), TILE_DIM));
                    }
                    col++;
                }
                row++;
            }
        } catch (final IOException e) {
        }
    }
}

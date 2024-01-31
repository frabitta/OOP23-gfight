package gfight.world.impl.map;

import org.locationtech.jts.geom.Coordinate;

import gfight.world.api.map.GameTile;

/**
 * Standard implementation of a GameTile.
 */
public final class GameTileImpl implements GameTile {

    private final GameTile.TileType type;
    private final Coordinate centerPosition;
    private final double dimension;

    /**
     * Creates a new GameTile of the given type.
     * 
     * @param type      the type of the tile, see
     *                  {@link gfight.world.api.map.GameTile.TileType}
     * @param position  the position of the center of the tile
     * @param dimension the dimension of the side of the tile
     */
    public GameTileImpl(final GameTile.TileType type, final Coordinate centerPosition, final double dimension) {
        this.type = type;
        this.centerPosition = centerPosition;
        this.dimension = dimension;
    }

    @Override
    public TileType getType() {
        return this.type;
    }

    @Override
    public Coordinate getPosition() {
        return this.centerPosition;
    }

    @Override
    public boolean isWithin(final Coordinate position) {
        final var centerOffset = this.dimension / 2;
        return position.getX() <= this.centerPosition.getX() + centerOffset
                && position.getX() >= this.centerPosition.getX() - centerOffset
                && position.getY() <= this.centerPosition.getY() + centerOffset
                && position.getY() >= this.centerPosition.getY() - centerOffset;
    }
}

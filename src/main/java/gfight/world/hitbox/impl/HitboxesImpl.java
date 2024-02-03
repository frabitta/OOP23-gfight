package gfight.world.hitbox.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.prep.PreparedPolygon;
import org.locationtech.jts.geom.util.AffineTransformation;

import gfight.world.api.CachedGameEntity;
import gfight.world.api.GameEntity;
import gfight.world.hitbox.api.Hitboxes;

/**
 * An implementation of Hitbox Interface.
 */
public final class HitboxesImpl implements Hitboxes {

    @Override
    public Polygon getGeometry(final List<Coordinate> vertexes) {
        final GeometryFactory factory = new GeometryFactory();
        final List<Coordinate> polygon = new ArrayList<>(vertexes);
        polygon.add(polygon.get(0));
        return factory.createPolygon(polygon.toArray(new Coordinate[0]));
    }

    @Override
    public boolean isColliding(final Polygon collider, final Polygon coollided) {
        final PreparedPolygon myObject = new PreparedPolygon(collider);
        return myObject.intersects(coollided);
    }

    @Override
    public Polygon getSquare(final Coordinate centre, final double side) {
        final double halfSide = side / 2.0;
        final List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(centre.getX() - halfSide, centre.getY() - halfSide));
        coordinates.add(new Coordinate(centre.getX() + halfSide, centre.getY() - halfSide));
        coordinates.add(new Coordinate(centre.getX() + halfSide, centre.getY() + halfSide));
        coordinates.add(new Coordinate(centre.getX() - halfSide, centre.getY() + halfSide));
        return getGeometry(coordinates);
    }

    @Override
    public List<Coordinate> rotate(final List<Coordinate> polygon, final double theta) {
        final Polygon poligon = getGeometry(polygon);
        final AffineTransformation rotation = AffineTransformation.rotationInstance(theta);
        final List<Coordinate> rotatedPolygon = Arrays.asList(rotation.transform(poligon).getCoordinates());
        if (!rotatedPolygon.isEmpty()) {
            return new ArrayList<>(rotatedPolygon.subList(0, rotatedPolygon.size() - 1));
        } else {
            return new ArrayList<>();
        }
    }
    
    @Override
    public void freeHitboxes(final Set<CachedGameEntity> gaemobjects) {
        gaemobjects.stream().forEach(CachedGameEntity::reset);
    }

    @Override
    public Map<GameEntity, Set<GameEntity>> getAllCollision(final Set<GameEntity> gameObjects) {
        final Map<GameEntity, Set<GameEntity>> collisionMap = new LinkedHashMap<>();
        gameObjects.stream().forEach(entity -> collisionMap.put(entity, entity.getAllCollided(gameObjects)));
        return collisionMap;
    }
}

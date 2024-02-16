package gfight.world.hitbox.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.prep.PreparedPolygon;
import org.locationtech.jts.geom.util.AffineTransformation;
import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.common.impl.Position2DImpl;
import gfight.common.impl.VectorImpl;
import gfight.world.hitbox.api.Hitbox;
import gfight.world.hitbox.api.Hitboxes;

/**
 * An implementation of Hitbox Interface.
 */
public final class HitboxesImpl implements Hitboxes {

    @Override
    public boolean isColliding(final Hitbox collider, final Hitbox coollided) {
        final PreparedPolygon myObject = new PreparedPolygon(collider.getPolygonalHitbox());
        return myObject.intersects(coollided.getPolygonalHitbox());
    }

    @Override
    public List<Position2D> rotate(final List<Position2D> polygon, final double theta, final Position2D center) {
        if (polygon.isEmpty()) {
            return new ArrayList<>();
        }
        final Hitbox hitbox = new HitboxImpl(polygon);
        final AffineTransformation rotation = AffineTransformation.rotationInstance(theta, center.getX(), center.getY());
        final Coordinate[] rotatedCoordinates = rotation.transform(hitbox.getPolygonalHitbox()).getCoordinates();
        return Arrays.stream(rotatedCoordinates)
                .map(coordinate -> new Position2DImpl(coordinate.getX(), coordinate.getY()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Position2D> rotateTo(final List<Position2D> polygon, final Vect pointingDir, final Position2D center,
            final Position2D target) {
        final Vect distance = new VectorImpl(center, target);
        double rotation = pointingDir.anglecalc(distance);
        if (pointingDir.getX() * distance.getY() - pointingDir.getY() * distance.getX() < 0) {
            rotation = -rotation;
        }
        return rotate(polygon, rotation, center);
    }
}

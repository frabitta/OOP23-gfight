package gfight.view.impl;

import java.awt.Graphics2D;
import java.util.stream.Stream;

import gfight.common.Position2D;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.ViewableCamera;
import gfight.engine.graphics.impl.PolygonGraphicsComponent;

public class PolygonGraphicsRenderer extends AbstractGraphicsComponentRenderer {

    @Override
    boolean isCompValid(GraphicsComponent gComp) {
        return gComp instanceof PolygonGraphicsComponent;
    }

    @Override
    void renderComp(Graphics2D g, ViewableCamera camera) {
        //Stream<Position2D> pointsStream = gComp.getPositions().stream().map(pos -> camera.getRelativePosition(pos));
        g.fillPolygon(
            gComp.getPositions().stream().map(pos -> camera.getRelativePosition(pos)).mapToInt(p -> p.getX()).toArray(),
            gComp.getPositions().stream().map(pos -> camera.getRelativePosition(pos)).mapToInt(p -> p.getY()).toArray(),
            gComp.getPositions().size()
        );
    }

}

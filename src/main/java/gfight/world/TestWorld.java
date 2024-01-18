package gfight.world;

import java.util.List;

import gfight.common.Pair;
import gfight.view.GraphicsComponent;
import gfight.view.GraphicsComponent.EngineColor;
import gfight.view.ShapeGraphicsComponent;
import gfight.view.TextGraphicsComponent;

import java.util.stream.Stream;

/**
 * A test implementation of the World interface.
 */
public class TestWorld implements World {

    private int counter;
    private TextGraphicsComponent counterGraph;
    private List<GraphicsComponent> elements;
    private MovableCamera camera;

    @Override
    public void instantiate() {
        this.counter = 0;
        this.elements = List.<GraphicsComponent>of(
            new ShapeGraphicsComponent(EngineColor.BLUE, new Pair(250, 250), null, ShapeGraphicsComponent.ShapeType.CIRCLE, 40, 40),
            new ShapeGraphicsComponent(EngineColor.RED, new Pair(100, 100), null, ShapeGraphicsComponent.ShapeType.RECTANGLE, 100, 100),
            new TextGraphicsComponent(EngineColor.BLACK, new Pair(40, 60), null, "Banco di prova")
        );
        counterGraph = new TextGraphicsComponent(EngineColor.YELLOW,  new Pair(300, 300), null, String.valueOf(counter));
    }

    @Override
    public void update(final long deltaTime) {
        this.counter++;
        counterGraph.setText(String.valueOf(counter));
    }

    @Override
    public List<GraphicsComponent> getGraphicsComponents() {
        return Stream.concat(this.elements.stream(), Stream.of(counterGraph)).toList();
    }

    @Override
    public void installCamera(MovableCamera camera) {
        this.camera = camera;
    }

}

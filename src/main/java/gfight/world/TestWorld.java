package gfight.world;

import java.util.List;

import gfight.common.Pair;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.GraphicsComponentsFactory;
import gfight.engine.graphics.api.MovableCamera;
import gfight.engine.graphics.api.GraphicsComponent.EngineColor;
import gfight.engine.graphics.impl.GraphicsComponentsFactoryImpl;
import gfight.engine.graphics.impl.TextGraphicsComponent;

import java.util.stream.Stream;

/**
 * A test implementation of the World interface.
 */
public class TestWorld implements World {

    private GraphicsComponentsFactory factory = new GraphicsComponentsFactoryImpl();

    private int counter;
    private TextGraphicsComponent counterGraph;
    private List<GraphicsComponent> elements;
    private MovableCamera camera;

    @Override
    public void instantiate() {
        this.counter = 0;
        this.elements = List.<GraphicsComponent>of(
            factory.polygon(EngineColor.BLUE, List.of(
                new Pair(10, 10), new Pair(20, 15), new Pair(10, 20)
            )),
            factory.polygon(EngineColor.YELLOW, List.of(
                new Pair(50, 25), new Pair(60, 20), new Pair(60, 30)
            ))
        );
        counterGraph = factory.text(EngineColor.BLACK, new Pair(100, 130), 25, "0");
    }

    @Override
    public void update(final long deltaTime) {
        this.counter++;
        counterGraph.setText(String.valueOf(counter));

       // this.camera.moveTo(new Pair(counter,0));
    }

    @Override
    public List<GraphicsComponent> getGraphicsComponents() {
        return Stream.concat(this.elements.stream(), Stream.of(counterGraph)).toList();
    }

    @Override
    public void installCamera(MovableCamera camera) {
        this.camera = camera;
        this.camera.moveTo(new Pair(counter,0));
    }

}

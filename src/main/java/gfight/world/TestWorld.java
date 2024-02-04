package gfight.world;

import java.util.Collections;
import java.util.List;

import gfight.common.impl.Position2DImpl;
import gfight.common.impl.VectorImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.GraphicsComponentsFactory;
import gfight.engine.graphics.api.MovableCamera;
import gfight.engine.graphics.api.GraphicsComponent.EngineColor;
import gfight.engine.graphics.impl.GraphicsComponentsFactoryImpl;
import gfight.engine.graphics.impl.TextGraphicsComponent;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventMouse;
import gfight.world.api.MovingEntity;
import gfight.world.impl.EntityFactoryImpl;
import gfight.world.weapon.api.Team;

import java.util.stream.Stream;

/**
 * A test implementation of the World interface.
 */
public class TestWorld implements World {

    final private GraphicsComponentsFactory factory = new GraphicsComponentsFactoryImpl();

    private int counter;
    private TextGraphicsComponent counterGraph;
    private List<GraphicsComponent> elements;
    private MovableCamera camera;

    private MovingEntity projectileTesting = new EntityFactoryImpl().createProjectile(Team.ENEMY, new Position2DImpl(100,0), new VectorImpl(0, 1));

    @Override
    public void initialize() {
        this.counter = 0;
        this.elements = List.<GraphicsComponent>of(
            factory.polygon(EngineColor.BLUE, List.of(
                new Position2DImpl(10, 10), new Position2DImpl(20, 15), new Position2DImpl(10, 20)
            )),
            factory.polygon(EngineColor.YELLOW, List.of(
                new Position2DImpl(50, 25), new Position2DImpl(60, 20), new Position2DImpl(60, 30)
            ))
        );
        counterGraph = factory.text(EngineColor.BLACK, new Position2DImpl(100, 130), 25, "0");
    }

    @Override
    public void update(final long deltaTime) {
        this.counter++;
        counterGraph.setText(String.valueOf(counter));
        projectileTesting.updatePos(deltaTime, Collections.emptySet());
        System.out.println(projectileTesting.getPosition());
       // this.camera.moveTo(new Pair(counter,0));
    }

    @Override
    public List<GraphicsComponent> getGraphicsComponents() {
        return Stream.concat(this.elements.stream(), Stream.of(counterGraph, projectileTesting.getGraphics())).toList();
    }

    @Override
    public void installCamera(final MovableCamera camera) {
        this.camera = camera;
    }

    @Override
    public void processInput(InputEvent event) {
        if (event instanceof InputEventMouse) {
            var mouseEv = (InputEventMouse) event;
            counterGraph.setPositions(List.of(mouseEv.getPosition()));
        }
    }

}

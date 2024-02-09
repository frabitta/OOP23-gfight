package gfight.world;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gfight.common.api.Position2D;
import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.GraphicsComponentsFactory;
import gfight.engine.graphics.api.WorldCamera;
import gfight.engine.graphics.impl.GraphicsComponentsFactoryImpl;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventKey;
import gfight.engine.input.impl.InputEventFactoryImpl;
import gfight.world.entity.api.CachedGameEntity;
import gfight.world.entity.api.EntityFactory;
import gfight.world.entity.api.MovingEntity;
import gfight.world.entity.api.VertexCalculator;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.entity.impl.VertexCalculatorImpl;
import gfight.world.hitbox.api.Hitboxes;
import gfight.world.hitbox.impl.HitboxesImpl;
import gfight.world.map.api.GameMap;
import gfight.world.map.impl.GameMapImpl;
import gfight.world.movement.api.InputMovement;
import gfight.world.movement.api.MovementFactory;
import gfight.world.movement.impl.MovementFactoryImpl;

public class TestWorldImpl implements World {

    private final GraphicsComponentsFactory graphicsFactory;
    private final MovementFactory movementFactory;
    private final EntityFactory entityFactory;
    private final VertexCalculator vertexCalculator;
    private MovingEntity testEntity, testEntity2;
    private CachedGameEntity testWall, testWall2;
    private GameMap map;
    private WorldCamera camera;
    private final Set<InputEvent> pressed = new HashSet<>();
    private InputMovement movement, movement2;
    private final Hitboxes hitboxes;

    public TestWorldImpl() {
        this.graphicsFactory = new GraphicsComponentsFactoryImpl();
        this.movementFactory = new MovementFactoryImpl();
        this.entityFactory = new EntityFactoryImpl();
        this.vertexCalculator = new VertexCalculatorImpl();
        this.hitboxes = new HitboxesImpl();
    }

    @Override
    public void installCamera(WorldCamera camera) {
        this.camera = camera;
        this.camera.moveTo(new Position2DImpl(0, 0));
    }

    @Override
    public void initialize() {
        this.movement = movementFactory.createInput();
        this.movement2 = movementFactory.createInput();
        //this.map = new GameMapImpl(10);
        final List<Position2D> square = List.of(new Position2DImpl(0, 0), new Position2DImpl(0, 20),
                new Position2DImpl(20, 20), new Position2DImpl(20, -20));
        final List<Position2D> triangleCalculated = this.vertexCalculator.triangle(15, new Position2DImpl(50, 50));
        // System.out.println(triangleCalculated);
        this.testEntity = this.entityFactory.createPlayer(15, new Position2DImpl(50, 60), 0, movement);
        this.testEntity2 = this.entityFactory.createPlayer(15, new Position2DImpl(450, 40), 0, movement2);
        this.testWall = this.entityFactory.createObstacle(20, new Position2DImpl(250, 250));
        this.testWall2 = this.entityFactory.createObstacle(20, new Position2DImpl(250, 270));

    }

    @Override
    public void update(long deltaTime) {
        // System.out.println(this.testEntity.getPosition());
        if (!this.pressed.isEmpty()) {
            // System.out.println("deltaTime: " + deltaTime + "ms and moving...");
            this.hitboxes.freeHitboxes(Set.of(this.testEntity, this.testEntity2));
            this.testEntity.updatePos(deltaTime,
                    Set.of(this.testEntity, this.testEntity2, this.testWall, this.testWall2));
            this.testEntity2.updatePos(deltaTime,
                    Set.of(this.testEntity, this.testEntity2, this.testWall, this.testWall2));
        }
    }

    @Override
    public List<GraphicsComponent> getGraphicsComponents() {
        return List.of(this.testEntity.getGraphics(), this.testEntity2.getGraphics(), this.testWall.getGraphics(),
                this.testWall2.getGraphics());
    }

    @Override
    public void processInput(InputEvent event) {
        if (event.getType() == InputEvent.Type.PRESSED) {
            this.pressed.add(event);
            this.movement.addDirection(InputMovement.Directions.EAST);
            this.movement.addDirection(InputMovement.Directions.NORTH);
            this.movement2.addDirection(InputMovement.Directions.WEST);
        }
        if (event.getType() == InputEvent.Type.RELEASED) {
            this.pressed.remove(new InputEventFactoryImpl().pressedKey(((InputEventKey) event).getKey()));
            this.movement.removeDirection(InputMovement.Directions.EAST);
            this.movement.removeDirection(InputMovement.Directions.NORTH);
            this.movement2.removeDirection(InputMovement.Directions.WEST);
        }
    }
}

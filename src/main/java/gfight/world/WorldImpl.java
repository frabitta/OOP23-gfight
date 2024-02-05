package gfight.world;

import java.util.List;
import java.util.Optional;

import gfight.common.impl.Position2DImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.MovableCamera;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventKey;
import gfight.world.api.EntityManager;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;
import gfight.world.entity.impl.EntityFactoryImpl;
import gfight.world.hitbox.api.Hitboxes;
import gfight.world.hitbox.impl.HitboxesImpl;
import gfight.world.impl.EntityManagerImpl;
import gfight.world.map.api.GameMap;
import gfight.world.map.impl.GameMapImpl;
import gfight.world.movement.api.InputMovement;
import gfight.world.movement.impl.MovementFactoryImpl;

public class WorldImpl implements World {

    private MovableCamera camera;
    private EntityManager entityManager;
    private GameMap map;
    private InputMovement keyMapper;
    private Hitboxes hitboxManager;

    public WorldImpl() {
        this.entityManager = new EntityManagerImpl(new EntityFactoryImpl());
        this.hitboxManager = new HitboxesImpl();
        this.map = new GameMapImpl(10);
        this.keyMapper = new MovementFactoryImpl().createInput();

        // seguite questo esempio se volete creare entità di prova, basta 1 riga per entità
        // this.entityManager.createPlayer(15, new Position2DImpl(250, 250), 0, keyMapper);
    }

    @Override
    public void installCamera(final MovableCamera camera) {
        this.camera = camera;
        this.camera.moveTo(new Position2DImpl(0, 0));
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update(final long deltaTime) {
        this.hitboxManager.freeHitboxes(this.entityManager.getEntities());
        for (final var entity : this.entityManager.getEntities()) {
            if (entity instanceof MovingEntity) {
                ((MovingEntity) entity).updatePos(deltaTime, this.entityManager.getEntities());
            }
        }
    }

    @Override
    public List<GraphicsComponent> getGraphicsComponents() {
        return this.entityManager.getEntities().stream().map(GameEntity::getGraphics).toList();
    }

    @Override
    public void processInput(final InputEvent event) {
        if (event instanceof InputEventKey) {
            manageKey((InputEventKey) event);
        }
    }

    private void manageKey(final InputEventKey key) {
        final var direction = Optional.ofNullable(switch (key.getKey()) {
            case 87 -> InputMovement.Directions.NORTH;
            case 83 -> InputMovement.Directions.SOUTH;
            case 65 -> InputMovement.Directions.WEST;
            case 68 -> InputMovement.Directions.EAST;
            default -> null;
        });
        if (direction.isPresent()) {
            if (key.getType() == InputEvent.Type.PRESSED) {
                this.keyMapper.addDirection(direction.get());
            }
            if (key.getType() == InputEvent.Type.RELEASED) {
                this.keyMapper.removeDirection(direction.get());
            }
        }
    }
}

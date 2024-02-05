package gfight.world;

import java.util.*;
import java.util.stream.Collectors;

import gfight.common.api.Vect;
import gfight.common.impl.Position2DImpl;
import gfight.common.impl.VectorImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.engine.graphics.api.MovableCamera;
import gfight.engine.input.api.InputEvent;
import gfight.engine.input.api.InputEventMouse;
import gfight.world.api.ActiveEntity;
import gfight.world.api.EntityFactory;
import gfight.world.api.GameEntity;
import gfight.world.api.MovingEntity;
import gfight.world.impl.EntityFactoryImpl;
import gfight.world.movement.api.InputMovement;
import gfight.world.weapon.api.Team;
import gfight.world.weapon.api.Weapon;
import gfight.world.weapon.impl.WeaponImpl;

/**
 * A test implementation of the World interface.
 */
public class TestWorld implements World {

    private final EntityFactory factory = new EntityFactoryImpl();

    private Set<MovingEntity> entities = new HashSet<>();
    private MovableCamera camera;
    private Weapon gun = new WeaponImpl(200);
    private ActiveEntity player = factory.createPlayer(30, new Position2DImpl(50, 100), 10, new InputMovement() {

        @Override
        public void update() {
        }

        @Override
        public Vect getDirection() {
            return new VectorImpl(1, 0);
        }

        @Override
        public void setDirection(Vect direction) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setDirection'");
        }

        @Override
        public void addDirection(Directions dir) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addDirection'");
        }

        @Override
        public void removeDirection(Directions dir) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'removeDirection'");
        }
        
    });

    @Override
    public void initialize() {
        gun.setTeam(Team.ENEMY);
        gun.setParentEntity(player);
    }

    @Override
    public void update(final long deltaTime) {
        entities.stream().forEach(en -> en.updatePos(deltaTime, entities.stream().map(movEn -> (GameEntity) movEn).collect(Collectors.toSet())));
        if (entities.size() > 50) {
            entities.clear();
        }
    }

    @Override
    public List<GraphicsComponent> getGraphicsComponents() {
        return entities.stream().map(el -> el.getGraphics()).toList();
    }

    @Override
    public void installCamera(final MovableCamera camera) {
        this.camera = camera;
    }

    @Override
    public void processInput(final InputEvent event) {
        if (event instanceof InputEventMouse) {
            final var mouseEv = (InputEventMouse) event;
            if (mouseEv.getType() == InputEvent.Type.MOUSE_DOWN) {
                var proj = gun.shoot();
                if (Optional.ofNullable(proj).isPresent()) {
                    entities.add(proj);
                }
            }
        }
    }

}

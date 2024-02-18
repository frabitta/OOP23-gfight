package gfight.world.movement.impl;

import gfight.common.api.Vect;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.GameEntity;
import gfight.world.map.api.GameMap;
import gfight.world.movement.api.InputMovement;
import gfight.world.movement.api.MovementFactory;

/**
 * Implementation of the Movement Factory.
 */
public final class MovementFactoryImpl implements MovementFactory {

    @Override
    public BfsMovement createBfsMovement(final GameEntity target, final Character agent, final GameMap map, final double speed) {
        return new BfsMovement(agent, target, map, speed);
    }

    @Override
    public LinearMovement createLinearMovement(final Vect direction) {
        return new LinearMovement(direction);
    }

    @Override
    public Fixed createFixed() {
        return new Fixed();
    }

    @Override
    public InputMovement createInput() {
        return new InputMovementImpl();
    }
}

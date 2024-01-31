package gfight.world.api;

import gfight.world.impl.ActiveEntityImpl;
import gfight.world.movement.api.Movement;

public interface EntityBuilder {

    EntityBuilder addMovement(Movement movement);

    ActiveEntityImpl build() throws IllegalStateException;
}

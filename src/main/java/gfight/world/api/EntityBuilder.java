package gfight.world.api;

import gfight.world.impl.ActiveEntityImpl;

public interface EntityBuilder {

    EntityBuilder addMovement(Movement movement);

    ActiveEntityImpl build() throws IllegalStateException;
}

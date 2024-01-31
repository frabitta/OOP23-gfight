package gfight.world.impl;

import java.util.*;

import org.locationtech.jts.geom.Coordinate;

import gfight.view.GraphicsComponent;
import gfight.world.api.ActiveEntity;
import gfight.world.api.EntityBuilder;
import gfight.world.movement.api.Movement;


public class EntityBuilderImpl implements EntityBuilder{
    private final List<Coordinate> vertexes;
    private final Coordinate position;
    private final GraphicsComponent graphicsComponent;
    private final int health;
    Optional<Movement> movement = Optional.empty();

    public EntityBuilderImpl(List<Coordinate> vertexes, Coordinate position, GraphicsComponent graphicsComponent, int health){
        this.vertexes = vertexes;
        this.position = position;
        this.graphicsComponent = graphicsComponent;
        this.health = health;
    }

	@Override
	public EntityBuilder addMovement(Movement movement) {
		this.movement = Optional.ofNullable(movement);
        return this;
	}

	@Override
	public ActiveEntityImpl build() throws IllegalStateException {
		if (this.vertexes == null || this.position == null || this.graphicsComponent == null){
            throw new IllegalStateException();
        }
        return new ActiveEntityImpl(vertexes, position, graphicsComponent, movement, health);
	}
    
}

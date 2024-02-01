package gfight.world.impl;

import java.util.Optional;
import gfight.world.api.Director;
import gfight.world.api.EntityBuilder;
import gfight.world.movement.impl.BaseMovement;
import gfight.world.movement.impl.IabfsMovement;

public class DirectorImpl implements Director{

	@Override
	public void chestContructor(EntityBuilder builder) {
		
	}

	@Override
	public void enemyContructor(EntityBuilder builder) {
		//builder.addMovement();
	}

	@Override
	public void playerContructor(EntityBuilder builder) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'playerContructor'");
	}
    
}

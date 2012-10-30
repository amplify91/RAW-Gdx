package com.detourgames.raw.game;

import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class StateSecurityBot extends StateComponent{

	public StateSecurityBot() {
		super(new int[]{});
		
	}

	@Override
	public void update(PhysicsComponent physics, long nanoTime) {
		updateTempState(nanoTime);
		
	}

	@Override
	public void setInitialState() {
		// TODO Auto-generated method stub
		
	}
	
}

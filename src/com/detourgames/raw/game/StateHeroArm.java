package com.detourgames.raw.game;

import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class StateHeroArm extends StateComponent{
	
	public StateHeroArm() {
		super(new int[]{STATE_SHOOTING});
	}

	@Override
	public void update(PhysicsComponent physics) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setInitialState() {
		// TODO Auto-generated method stub
		setState(STATE_SHOOTING);
		
	}
	
}

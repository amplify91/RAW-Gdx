package com.detourgames.raw.game;

import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class StateHeroProjectile extends StateComponent{
	
	public static final int STATE_IDLE = 0;
	
	public StateHeroProjectile() {
		super(new int[]{STATE_IDLE});
	}

	@Override
	public void update(PhysicsComponent physics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInitialState() {
		// TODO Auto-generated method stub
		
	}

}

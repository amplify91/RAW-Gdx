package com.detourgames.raw.game;

import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class StateTurret extends StateComponent{
	
	public StateTurret() {
		super(new int[]{STATE_HURTING, STATE_IDLE,  STATE_SHOOTING, STATE_DEAD});
	}

	@Override
	public void update(PhysicsComponent physics, long nanoTime) {
		updateTempState(nanoTime);
		if(mHealth==0){
			setState(STATE_DEAD);
			physics.getParentSprite().die();
		}
	}
	
	@Override
	public void setInitialState() {
		mMaxHealth = 300;
		mHealth = mMaxHealth;
		setState(STATE_IDLE);
	}
	
}

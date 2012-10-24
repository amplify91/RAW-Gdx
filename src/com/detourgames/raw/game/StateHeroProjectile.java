package com.detourgames.raw.game;

import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class StateHeroProjectile extends StateComponent{
	
	public static final int STATE_IDLE = StateComponent.STATE_IDLE;
	public static final int STATE_DEAD = StateComponent.STATE_DEAD;
	
	public StateHeroProjectile() {
		super(new int[]{STATE_IDLE, STATE_DEAD});
	}

	@Override
	public void update(PhysicsComponent physics, long nanoTime) {
		updateTempState(nanoTime);
		if(mState==STATE_DEAD){
			physics.getParentSprite().die();
		}
	}

	@Override
	public void setInitialState() {
		mMaxHealth = 1;
		mHealth = mMaxHealth;
		mState = STATE_IDLE;
		
	}

}

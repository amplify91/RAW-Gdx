package com.detourgames.raw.turretElement;

import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class StateTurret extends StateComponent{
	
	public StateTurret() {
		super(new int[]{STATE_HURTING, STATE_IDLE,  STATE_SHOOTING});
	}

	@Override
	public void update(PhysicsComponent physics) {
	}
	
	@Override
	protected void setInitialState() {
		mMaxHealth = 100;
		mHealth = mMaxHealth;
		setState(STATE_IDLE);
	}
	
}

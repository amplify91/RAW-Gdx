package com.detourgames.raw;

public class StateHero extends StateComponent{

	public StateHero() {
		super(new int[]{STATE_DASHING, STATE_DOUBLE_JUMPING, STATE_FALLING,
				STATE_HURTING, STATE_IDLE, STATE_JUMPING, STATE_RAWESOME,
				STATE_RECOVERING, STATE_RUNNING, STATE_SHOOTING, STATE_SWINGING});
	}

	@Override
	protected void setInitialState() {
		mMaxHealth = 100;
		mHealth = mMaxHealth;
		mState = STATE_IDLE;
	}
	
}

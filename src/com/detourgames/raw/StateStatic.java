package com.detourgames.raw;

public class StateStatic extends StateComponent{
	
	public StateStatic() {
		super(new int[]{STATE_IDLE});
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setInitialState() {
		mMaxHealth = 0;
		mHealth = mMaxHealth;
		mState = STATE_IDLE;
	}
	
}

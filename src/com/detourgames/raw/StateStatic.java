package com.detourgames.raw;

public class StateStatic extends StateComponent{
	
	public StateStatic() {
		super(new int[]{STATE_IDLE, STATE_HURTING, STATE_DEAD});
		// TODO Auto-generated constructor stub
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

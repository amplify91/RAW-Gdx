package com.detourgames.raw;

public class StateNone extends StateComponent{

	public StateNone() {
		super(new int[]{0});
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(PhysicsComponent physics, long nanoTime) {
		// do nothing.
		
	}

	@Override
	public void setInitialState() {
		setState(0);
		
	}

}

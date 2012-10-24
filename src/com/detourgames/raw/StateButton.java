package com.detourgames.raw;

public class StateButton extends StateComponent{
	
	public static final int STATE_JUMP_UP = 1;
	public static final int STATE_JUMP_DOWN = 2;
	public static final int STATE_DASH_UP = 3;
	public static final int STATE_DASH_DOWN = 4;
	public static final int STATE_DASH_UNAVAILABLE = 5;
	
	public StateButton() {
		super(new int[]{STATE_JUMP_UP,STATE_JUMP_DOWN,STATE_DASH_UP,STATE_DASH_DOWN,STATE_DASH_UNAVAILABLE});
	}

	@Override
	public void update(PhysicsComponent physics, long nanoTime) {
		updateTempState(nanoTime);
		
	}

	@Override
	public void setInitialState() {
		setState(STATE_JUMP_UP);
	}

}

package com.detourgames.raw;

public abstract class StateComponent {
	
	int mMaxHealth;
	int mHealth;
	
	protected int mState = 0;
	
	private int[] mAvailableStates;
	
	public static final int STATE_IDLE = 1;
	public static final int STATE_RUNNING = 2;
	public static final int STATE_JUMPING = 3;
	public static final int STATE_DOUBLE_JUMPING = 4;
	public static final int STATE_FALLING = 5;
	public static final int STATE_DASHING = 6;
	public static final int STATE_SHOOTING = 7;
	public static final int STATE_SWINGING = 8;
	public static final int STATE_HURTING = 9;
	public static final int STATE_RECOVERING = 10;
	public static final int STATE_RAWESOME = 11;
	
	public StateComponent(int[] availableStates){
		setAvailableStates(availableStates);
		setInitialState();
	}
	
	public void update(PhysicsComponent physics){
		//physics.getBody().getFixtureList().
	}
	
	public void reset(){
		setInitialState();
	}
	
	public int getState(){
		return mState;
	}
	
	public void setState(int state){
		
		if(isStateAvailable(state)){
			mState = state;
		}else{
			System.out.println("WARNING! Unavailable State!");
		}
		
	}
	
	private boolean isStateAvailable(int state){
		for(int i=0;i<mAvailableStates.length-1;i++){
			if(mAvailableStates[i]==state){
				return true;
			}
		}
		return false;
	}
	
	private void setAvailableStates(int[] states){
		mAvailableStates = states;
	}
	
	protected abstract void setInitialState(); // initialize fields like mHealth
	
}

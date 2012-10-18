package com.detourgames.raw;

public abstract class StateComponent {
	
	public int mMaxHealth;
	public int mHealth;
	
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
	public static final int STATE_DEAD = 11;
	public static final int STATE_RAWESOME = 12;
	
	public StateComponent(int[] availableStates){
		setAvailableStates(availableStates);
		setInitialState();
	}
	
	public abstract void update(PhysicsComponent physics);
	
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
			System.out.println("WARNING! Unavailable State!" + " State: " + state);
		}
		
	}
	
	private boolean isStateAvailable(int state){
		for(int i=0;i<mAvailableStates.length;i++){
			if(mAvailableStates[i]==state){
				return true;
			}
		}
		return false;
	}
	
	private void setAvailableStates(int[] states){
		mAvailableStates = states;
	}
	
	public abstract void setInitialState(); // initialize fields like mHealth
	
	public void causeDamage(Sprite target, int damage){
		target.mState.receiveDamage(damage);
	}
	
	public void receiveDamage(int damage){
		mHealth -= damage;
		setState(STATE_HURTING);
		if(mHealth<=0){
			mHealth = 0;
			setState(STATE_DEAD);
		}
	}
	
	public void kill(){
		mHealth = 0;
		setState(STATE_DEAD);
	}
	
}

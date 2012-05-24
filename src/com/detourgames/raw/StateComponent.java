package com.detourgames.raw;

public class StateComponent {
	
	int mMaxHealth;
	int mHealth;
	
	public StateComponent(){
		
	}
	
	public void update(PhysicsComponent physics){
		
	}
	
	public void reset(){
		mHealth = mMaxHealth;
	}
	
}

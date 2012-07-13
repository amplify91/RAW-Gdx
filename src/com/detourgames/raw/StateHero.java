package com.detourgames.raw;

public class StateHero extends StateComponent{
	
	//TODO Could move these to super class to avoid having to cast to (StateHero),
	//but it would pollute other classes that didnt need it.
	boolean isOnGround = false;
	boolean canJump = false;
	boolean canDoubleJump = false;
	
	public StateHero() {
		super(new int[]{STATE_DASHING, STATE_DOUBLE_JUMPING, STATE_FALLING,
				STATE_HURTING, STATE_IDLE, STATE_JUMPING, STATE_RAWESOME,
				STATE_RECOVERING, STATE_RUNNING, STATE_SHOOTING, STATE_SWINGING});
	}

	@Override
	public void update(PhysicsComponent physics) {
		if(isOnGround){
			canJump = true;
			canDoubleJump = true;
		}else{
			canJump=false;
			if(physics.mBody.getLinearVelocity().y>0){
				setState(STATE_JUMPING);
			}else{
				setState(STATE_FALLING);
			}
		}
	}
	
	@Override
	protected void setInitialState() {
		mMaxHealth = 100;
		mHealth = mMaxHealth;
		setState(STATE_IDLE);
	}
	
}

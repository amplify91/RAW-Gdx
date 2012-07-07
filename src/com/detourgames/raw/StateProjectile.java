package com.detourgames.raw;

public class StateProjectile extends StateComponent{

	public StateProjectile() {
		super(new int[]{STATE_HURTING, STATE_IDLE, STATE_DEAD});
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(PhysicsComponent physics) {
		if(mState==STATE_HURTING){
			
		}else if(mState==STATE_DEAD){
			((Projectile)physics.getParentSprite()).recycle();
		}
		
	}

	@Override
	protected void setInitialState() {
		// TODO Auto-generated method stub
		mState = STATE_IDLE;
	}

}

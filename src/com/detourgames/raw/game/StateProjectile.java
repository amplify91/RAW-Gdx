package com.detourgames.raw.game;

import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class StateProjectile extends StateComponent{

	public StateProjectile() {
		super(new int[]{STATE_HURTING, STATE_IDLE, STATE_DEAD});
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(PhysicsComponent physics) {
		if(mState==STATE_HURTING){
			
		}else if(mState==STATE_DEAD){
			//spawn explosion
			((Projectile)physics.getParentSprite()).recycle();
		}
		if(mHealth==0){
			setState(STATE_DEAD);
		}
		
	}

	@Override
	public void setInitialState() {
		// TODO Auto-generated method stub
		mMaxHealth = 1;
		mHealth = mMaxHealth;
		mState = STATE_IDLE;
	}

}

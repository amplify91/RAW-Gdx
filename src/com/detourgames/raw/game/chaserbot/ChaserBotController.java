package com.detourgames.raw.game.chaserbot;

import com.badlogic.gdx.utils.TimeUtils;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;
import com.detourgames.raw.game.ControllerTurret;
import com.detourgames.raw.game.PhysicsTurret;
import com.detourgames.raw.game.Turret;

public class ChaserBotController extends ControllerTurret {
	
	public ChaserBotController(Turret mTurret) {
		super(mTurret);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		getNextAction(state);
		if(state.getState()==StateComponent.STATE_SHOOTING && TimeUtils.nanoTime()>= mNextShot){
			mTurret.fireMissileAtTarget(mTarget);
			mLastShot = TimeUtils.nanoTime();
			mNextShot = mLastShot + mCooldown;
			state.setState(StateComponent.STATE_IDLE);
		}
		else if(state.getState()==StateComponent.STATE_RUNNING){
			((ChaserBotPhysicsComponent)physics).moveTowardsTarget(mTarget);
		}
	}
}

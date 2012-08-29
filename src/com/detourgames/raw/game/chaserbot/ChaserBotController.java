package com.detourgames.raw.game.chaserbot;

import com.badlogic.gdx.utils.TimeUtils;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;
import com.detourgames.raw.game.ControllerTurret;
import com.detourgames.raw.game.PhysicsTurret;

public class ChaserBotController extends ControllerTurret {
	
	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		getNextAction(state);
		if(state.getState()==StateComponent.STATE_SHOOTING && TimeUtils.nanoTime()>= mNextShot){
			((PhysicsTurret)physics).fireMissileAtTarget(mTarget.getPosition());
			mLastShot = TimeUtils.nanoTime();
			mNextShot = mLastShot + mCooldown;
			state.setState(StateComponent.STATE_IDLE);
		}
		else if(state.getState()==StateComponent.STATE_RUNNING){
			((ChaserBotPhysicsComponent)physics).moveTowardsTarget(mTarget);
		}
	}
}

package com.detourgames.raw.game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.StateComponent;

public class ControllerTurret extends ControllerComponent {
	
	protected Sprite mTarget;
	protected Turret mTurret;
	protected long mLastShot = TimeUtils.nanoTime();
	protected long mNextShot = 0;
	protected static final long mCooldown = 1000000000; //the amount of time between shots.
	
	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		getNextAction(state);
		if(state.getState()==StateComponent.STATE_SHOOTING && TimeUtils.nanoTime()>=mNextShot){
			mTurret.fireMissileAtTarget(mTarget);
			mLastShot = TimeUtils.nanoTime();
			mNextShot = mLastShot + mCooldown;
			state.setState(StateComponent.STATE_IDLE);
		}
	}
	
	public void getNextAction(StateComponent state){
		if(state.getState()!=StateComponent.STATE_DEAD){
			//find target (RAW)
			startAttackingTarget(state, GameManager.getGameManager().getLevel().getHero());
		}
	}
	
	public void startAttackingTarget(StateComponent state, Sprite target){
		state.setState(StateComponent.STATE_SHOOTING);
		mTarget = target;
	}
	public ControllerTurret(Turret mTurret)
	{
		this.mTurret=mTurret;
	}
}

package com.detourgames.raw.game;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.StateComponent;

public class ControllerTurret extends ControllerComponent {
	
	Sprite mTarget;
	
	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		getNextAction(state);
		if(state.getState()==StateComponent.STATE_SHOOTING){
			((PhysicsTurret)physics).fireMissileAtTarget(new Vector2(mTarget.getX(),mTarget.getY()));
			state.setState(StateComponent.STATE_IDLE);
		}
	}
	
	public void getNextAction(StateComponent state){
		
		//find target (RAW)
		startAttackingTarget(state, GameManager.getGameManager().getLevel().getHero());
	}
	
	public void startAttackingTarget(StateComponent state, Sprite target){
		state.setState(StateComponent.STATE_SHOOTING);
		mTarget = target;
	}
	
}

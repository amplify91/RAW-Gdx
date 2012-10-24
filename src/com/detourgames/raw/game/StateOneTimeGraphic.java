package com.detourgames.raw.game;

import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class StateOneTimeGraphic extends StateComponent{

	public StateOneTimeGraphic() {
		super(new int[]{0, STATE_DEAD});
	}

	@Override
	public void update(PhysicsComponent physics, long nanoTime) {
		updateTempState(nanoTime);
		if(physics.getParentSprite().getAnimationComponent().isAnimationFinished()){
			physics.getParentSprite().die();
			setState(STATE_DEAD);
		}
		
	}

	@Override
	public void setInitialState() {
		// TODO Auto-generated method stub
		
	}

}

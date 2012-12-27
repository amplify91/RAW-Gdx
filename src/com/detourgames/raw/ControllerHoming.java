package com.detourgames.raw;

import com.detourgames.raw.AI.steering.RaycastSteerer;
import com.detourgames.raw.AI.steering.Steerer;

public class ControllerHoming extends ControllerComponent{

	Sprite mTarget;
	float mTurningRate;

	public ControllerHoming(Sprite target, float turningRate){
		mTurningRate = turningRate;
		mTarget = target;
	}

	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		if(!RaycastSteerer.AvoidObstacles(physics.getBody(),mTarget.mPhysics.getBody().getFixtureList()))
			return;
			//physics.getBody().applyForceToCenter(Steerer.persue(physics.getParentSprite(), mTarget).nor().mul(mTurningRate));//todo fix
		}
	}
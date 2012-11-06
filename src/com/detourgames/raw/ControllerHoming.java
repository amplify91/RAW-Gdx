package com.detourgames.raw;

import java.util.Iterator;

import com.badlogic.gdx.physics.box2d.Body;
import com.detourgames.raw.game.HeroProjectile;
import com.detourgames.raw.steering.RaycastSteerer;
import com.detourgames.raw.steering.Steerer;

public class ControllerHoming extends ControllerComponent{

	Sprite mTarget;
	float mTurningRate;

	public ControllerHoming(Sprite target, float turningRate){
		mTurningRate = turningRate;
		mTarget = target;
	}

	@Override
	public void update(StateComponent state, PhysicsComponent physics){
				if(RaycastSteerer.AvoidObstacles(physics.getBody())==false)
					physics.getBody().applyForceToCenter(Steerer.persue(physics.getParentSprite(), mTarget).nor().mul(mTurningRate));
		}
	}
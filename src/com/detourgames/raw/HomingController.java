package com.detourgames.raw;

import java.util.Iterator;

import com.badlogic.gdx.physics.box2d.Body;
import com.detourgames.raw.steering.Steerer;

public class HomingController extends ControllerComponent{

	Sprite mTarget;
	float mTurningRate;
	
	public HomingController(Sprite target, float turningRate){
		mTurningRate = turningRate;
		mTarget = target;
	}
	
	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		Iterator<Body> bodies=GameManager.getGameManager().getLevel().getWorld().getBodies();
		Body toFlee=null;
		while(true){
			Body body = bodies.next();
			if(body.getPosition().dst(physics.getPosition())<5){
				toFlee=body;
				break;
			}
		}
		if(true){//toFlee==null)
			physics.getBody().applyForceToCenter(Steerer.persue(physics.getParentSprite(), mTarget).nor().mul(mTurningRate));
		}else{
			physics.getBody().applyForceToCenter(Steerer.flee(physics.getParentSprite(), toFlee).nor().mul(mTurningRate));
		}
	}
}

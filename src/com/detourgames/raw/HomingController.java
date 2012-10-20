package com.detourgames.raw;

import java.util.Iterator;

import com.badlogic.gdx.physics.box2d.Body;
import com.detourgames.raw.steering.Steerer;

public class HomingController extends ControllerComponent{

	PhysicsComponent target;
	float turningRate;
	public HomingController(PhysicsComponent target,float turningRate)
	{
		this.turningRate=turningRate;
		this.target=target;
	}
	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		Iterator<Body> bodies=GameManager.getGameManager().getLevel().getWorld().getBodies();
		Body toFlee=null;
		while(true)
		{
			Body body = bodies.next();
			if(body.getPosition().dst(physics.getPosition())<5)
			{
				toFlee=body;
				break;
			}
		}
		if(true)//toFlee==null)
			physics.getBody().applyForceToCenter(Steerer.Persue(physics, target).nor().mul(turningRate));
		else
			physics.getBody().applyForceToCenter(Steerer.Flee(physics, toFlee).nor().mul(turningRate));
	}
}

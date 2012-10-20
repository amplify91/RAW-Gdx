package com.detourgames.raw.steering;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.detourgames.raw.PhysicsComponent;

public class Steerer {
	public static Vector2 Persue(PhysicsComponent persuer, PhysicsComponent persued)
	{
		Vector2 currentVelocity=persuer.getBody().getLinearVelocity();
		Vector2 desiredVector=persued.getPosition().sub(persuer.getPosition());
		Vector2 steering = desiredVector.sub(currentVelocity);
		return steering;
	}
	public static Vector2 Flee(PhysicsComponent fleer, PhysicsComponent fleed)
	{
		Vector2 currentVelocity=fleer.getBody().getLinearVelocity();
		Vector2 desiredVector=fleed.getPosition().sub(fleer.getPosition()).mul(-1);
		Vector2 steering = desiredVector.sub(currentVelocity);
		return steering;
	}

	public static Vector2 Flee(PhysicsComponent fleer, Body fleed)
	{
		Vector2 currentVelocity=fleer.getBody().getLinearVelocity();
		Vector2 desiredVector=fleed.getPosition().sub(fleer.getPosition()).mul(-1);
		Vector2 steering = desiredVector.sub(currentVelocity);
		return steering;
	}
}

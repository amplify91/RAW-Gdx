package com.detourgames.raw.steering;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;

public class Steerer {
	
	public static Vector2 persue(Sprite persuer, Sprite persued){
		Vector2 currentVelocity = persuer.getBody().getLinearVelocity();
		Vector2 desiredVector = persued.getPosition().sub(persuer.getPosition());
		Vector2 steering = desiredVector.sub(currentVelocity);
		return steering;
	}
	
	public static Vector2 flee(Sprite fleer, Sprite fleed){
		Vector2 currentVelocity = fleer.getBody().getLinearVelocity();
		Vector2 desiredVector = fleed.getPosition().sub(fleer.getPosition()).mul(-1);
		Vector2 steering = desiredVector.sub(currentVelocity);
		return steering;
	}

	public static Vector2 flee(Sprite fleer, Body fleed){
		Vector2 currentVelocity = fleer.getBody().getLinearVelocity();
		Vector2 desiredVector = fleed.getPosition().sub(fleer.getPosition()).mul(-1);
		Vector2 steering = desiredVector.sub(currentVelocity);
		return steering;
	}
}

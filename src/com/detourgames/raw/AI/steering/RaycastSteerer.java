package com.detourgames.raw.AI.steering;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.detourgames.raw.GameManager;

public class RaycastSteerer implements IPathFinder{
	
	//returns false if no obstacle is found
	@Override
	public boolean AvoidObstacles(Body physics,ArrayList<Fixture>fixturesToIgnore){

		Vector2 linearVelocity=physics.getLinearVelocity().cpy();
		if(physics.getLinearVelocity().len()==0)
			return false;
		Vector2 currentPosition=physics.getPosition().cpy();
		Vector2 destination = physics.getPosition().cpy().add(linearVelocity.mul(.1f));
		ARaycastCallback callback = ARaycastCallback.RecycleRaycastCallback(fixturesToIgnore);
		GameManager.getGameManager().getLevel().getWorld().rayCast(callback,currentPosition, destination );

		Fixture fixtureContacted=callback.lastFixtureReturn;
		Vector2 pointOfIntersection=callback.lastPointReturn;
		Vector2 normal=callback.lastNormalReturn;

		if(fixtureContacted==null)
		{
			return false;
		}
		if(normal.len()>1.00001 || normal.len()<0.99999)
		{
			System.out.println("A calculated normal has length" + normal.len() +"in RaycastSteerer.java");
		}
		float speedTowardsFixtureLine=Math.abs(linearVelocity.dot(normal));

		ArrayList<Fixture>fixtures=physics.getFixtureList();
		float width=0;
		for (Fixture f:fixtures)
		{
			if(width<f.getShape().getRadius())
				width=f.getShape().getRadius();
		}
		
		float distanceFromFixtureLine=Math.abs(pointOfIntersection.cpy().sub(currentPosition).dot(normal))-width;
		float time=distanceFromFixtureLine/speedTowardsFixtureLine;
		if(distanceFromFixtureLine>linearVelocity.len())
		{
			@SuppressWarnings("unused")
			float len=linearVelocity.len();
			System.out.println("[distanceFromFixtureLine>linearVelocity.len] in RaycastSteerer.java");
		}
		Vector2 avoidingVelocity=normal.cpy().mul(normal.dot(linearVelocity));//todo figure out radius of body normal.cpy().mul(distanceFromFixtureLine-radius);
		Vector2 desiredVelocity = linearVelocity.cpy().sub(avoidingVelocity);
		float mass = physics.getMass();
		Vector2 acceleration=desiredVelocity.cpy().sub(linearVelocity).mul(time);
		Vector2 avoidingForce=acceleration.mul(mass);

		physics.applyForceToCenter(avoidingForce);
		Vector2 newVelocity=physics.getLinearVelocity();
		newVelocity.sub(new Vector2(0f,0f));
					
		return true;
	}
}

package com.detourgames.raw.steering;

import com.detourgames.raw.GameManager;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.PhysicsScrolling;
import com.detourgames.raw.game.BackgroundTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

public class RaycastSteerer implements RayCastCallback{

	//returns false if no obstacle is found
	public static boolean AvoidObstacles(Body physics){
		Vector2 linearVelocity=physics.getLinearVelocity().cpy();
		if(physics.getLinearVelocity().len()==0)
			return false;
		Vector2 currentPosition=physics.getPosition().cpy();
		Vector2 destination = physics.getPosition().cpy().add(linearVelocity);
		RayCastCallback output = GetSteerer();
		lastFixtureReturn=null;
		lastPointReturn=null;
		lastNormalReturn=null;
		lastFractionReturn=Float.MAX_VALUE;
		GameManager.getGameManager().getLevel().getWorld().rayCast(output,currentPosition, destination );
		
		Fixture fixtureContacted=lastFixtureReturn;
		Vector2 pointOfIntersection=lastPointReturn;
		Vector2 normal=lastNormalReturn;
		float distanceToCollision = lastFractionReturn;
		
		if(fixtureContacted!=null)
		{
			if(normal.len()!=1)
			{
				int m=0;
			}
			Vector2 velocity=destination.cpy().sub(currentPosition);
			float speedTowardsFixtureLine=Math.abs(velocity.dot(normal)/normal.len());
			float distanceFromFixtureLine=Math.abs(pointOfIntersection.cpy().sub(currentPosition).dot(normal)/normal.len());
			if(distanceFromFixtureLine>linearVelocity.len())
			{
				int m=0;
			}
			Vector2 avoidingVector=normal.cpy().mul((speedTowardsFixtureLine - distanceFromFixtureLine));
			float mass = physics.getMass();
			Vector2 avoidingForce=avoidingVector.cpy().mul(mass);
			physics.applyForceToCenter(avoidingForce);
			Vector2 newVelocity=physics.getLinearVelocity();
			return true;
		}
		return false;
	}
	private static RaycastSteerer steerer=new RaycastSteerer();
	public static RaycastSteerer GetSteerer(){return steerer;}
	final float TERMINATE_RAYCAST_ON_COLLISION=-1;
	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point,
			Vector2 normal, float fraction) {
		if(fixture==null)
			return 0;
		if(fraction>lastFractionReturn)
			return 1;
		if(fixture.isSensor())
			return 1;
		if( fixture.getUserData() instanceof BackgroundTile)
			return 1;
		if(fixture.getBody().getUserData() instanceof PhysicsScrolling)//ignore background
			return 1;

		lastFixtureReturn=fixture;
		lastPointReturn=point;
		lastNormalReturn=normal;
		lastFractionReturn=fraction;
			return 1;

	}
	public static Fixture lastFixtureReturn;
	public static Vector2 lastPointReturn;
	public static Vector2 lastNormalReturn;
	public static float lastFractionReturn;
}

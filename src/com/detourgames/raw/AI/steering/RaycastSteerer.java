package com.detourgames.raw.AI.steering;

import java.util.ArrayList;

import com.detourgames.raw.GameManager;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.PhysicsScrolling;
import com.detourgames.raw.PhysicsStatic;
import com.detourgames.raw.game.BackgroundTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

public class RaycastSteerer implements RayCastCallback{
	//returns false if no obstacle is found
	public static boolean AvoidObstacles(Body physics,ArrayList<Fixture>fixturesToIgnore){
		//physics.setAngularVelocity(20);
		Vector2 linearVelocity=physics.getLinearVelocity().cpy();
		if(physics.getLinearVelocity().len()==0)
			return false;
		Vector2 currentPosition=physics.getPosition().cpy();
		Vector2 destination = physics.getPosition().cpy().add(linearVelocity.mul(.1f));
		RayCastCallback output = GetSteerer(fixturesToIgnore);
		lastFixtureReturn=null;
		lastPointReturn=null;
		lastNormalReturn=null;
		lastFractionReturn=Float.MAX_VALUE;
		GameManager.getGameManager().getLevel().getWorld().rayCast(output,currentPosition, destination );

		Fixture fixtureContacted=lastFixtureReturn;
		Vector2 pointOfIntersection=lastPointReturn;
		Vector2 normal=lastNormalReturn;
		float distanceToCollision = lastFractionReturn;

		if(fixtureContacted==null)
		{
			return false;
		}
		if(normal.len()!=1)
		{
			int m=0;
		}
		float speedTowardsFixtureLine=Math.abs(linearVelocity.dot(normal));

		ArrayList<Fixture>fixtures=physics.getFixtureList();
		float width=0;
		for (Fixture f:fixtures)
		{
			if(width<f.getShape().getRadius())
				width=f.getShape().getRadius();
		}
		
		float distanceFromFixtureLine=Math.abs(pointOfIntersection.cpy().sub(currentPosition).dot(normal)-width);
		float time=distanceFromFixtureLine/speedTowardsFixtureLine;
		if(distanceFromFixtureLine>linearVelocity.len())
		{
			int m=0;
		}
		Vector2 avoidingVelocity=normal.cpy().mul(normal.dot(linearVelocity));//todo figure out radius of body normal.cpy().mul(distanceFromFixtureLine-radius);
		Vector2 desiredVelocity = linearVelocity.cpy().sub(avoidingVelocity);
		float mass = physics.getMass();
		Vector2 acceleration=desiredVelocity.cpy().sub(linearVelocity).mul(time);
		Vector2 avoidingForce=acceleration.mul(mass);
		
		//				Vector2 avoidingForce=avoidingVelocity.cpy().mul(mass*speedTowardsFixtureLine*speedTowardsFixtureLine/distanceFromFixtureLine/distanceFromFixtureLine);
//		physics.applyForceToCenter(avoidingForce);
					physics.setLinearVelocity(desiredVelocity);
				//	physics.setActive(false);
					Vector2 newVelocity=physics.getLinearVelocity();
					newVelocity.sub(new Vector2(0f,0f));
					
		return true;
	}
	private static RaycastSteerer steerer=new RaycastSteerer();
	public static RaycastSteerer GetSteerer(ArrayList<Fixture>fixturesToIgnore){
		RaycastSteerer.fixturesToIgnore=fixturesToIgnore;
		return steerer;}
	
	final float TERMINATE_RAYCAST_ON_COLLISION=-1;
	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point,
			Vector2 normal, float fraction) {
		if(fixture==null)
			return 0;
		if(fraction>lastFractionReturn)//this fixture is behind a fixture that was already found
			return 1;
		if(fixture.isSensor())//ignore sensors
			return 1;
		for(Fixture ignored:fixturesToIgnore)//ignore fixturesToIgnore
		{
			if(ignored==fixture)
				return 1;
		}
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
	public static ArrayList<Fixture> fixturesToIgnore;
	public static Fixture lastFixtureReturn;
	public static Vector2 lastPointReturn;
	public static Vector2 lastNormalReturn;
	public static float lastFractionReturn;
}

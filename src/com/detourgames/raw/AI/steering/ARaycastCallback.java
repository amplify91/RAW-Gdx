package com.detourgames.raw.AI.steering;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.detourgames.raw.PhysicsScrolling;
import com.detourgames.raw.game.BackgroundTile;

public class ARaycastCallback implements RayCastCallback{
	
	private static ARaycastCallback callback;
	public static ARaycastCallback RecycleRaycastCallback(ArrayList<Fixture>fixturesToIgnore){
		if(callback==null)
			callback=new ARaycastCallback();
		callback.fixturesToIgnore=fixturesToIgnore;
		callback.lastFixtureReturn=null;
		callback.lastPointReturn=null;
		callback.lastNormalReturn=null;
		callback.lastFractionReturn=Float.MAX_VALUE;
		return callback;}
	
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
	public ArrayList<Fixture> fixturesToIgnore;
	public Fixture lastFixtureReturn;
	public Vector2 lastPointReturn;
	public Vector2 lastNormalReturn;
	public float lastFractionReturn;
}
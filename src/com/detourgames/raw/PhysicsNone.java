package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsNone extends PhysicsComponent{
	
	//For sprites that might have no physics.
	
	public PhysicsNone(){
		mBody = null;
	}
	
	@Override
	public void update() {
		// do nothing.
	}
	
	@Override
	public Body getBody(){
		return null;
	}
	
	public void setPosition(float x, float y){
		mXPos = x;
		mYPos = y;
	}
	
	public void setAngle(float radians){
		mAngle = radians;
	}

}

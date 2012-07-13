package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsNone extends PhysicsComponent{
	
	//For sprites that might have no physics.
	//I can't think of an example because everything should have at least a position.
	
	@Override
	public void update() {
		// do nothing.
		
	}
	
	@Override
	public float getX() {
		return 0f;
	}
	
	@Override
	public float getY() {
		return 0f;
	}
	
	@Override
	public Body getBody(){
		return null;
	}

}

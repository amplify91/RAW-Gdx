package com.detourgames.raw;

public class ControllerNavBot extends ControllerComponent{
	
	Sprite mTarget;

	public ControllerNavBot(Sprite raw){
		mTarget = raw;
	}
	
	float targetX;
	float targetY;
	float x;
	float y;
	float tx;
	float ty;
	float dist;
	float velX;
	float velY;
	float speed = 1f;
	float radians;
	float px;
	float py;
	float pointLength = 1f;
	
	
	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		
		//TODO be sure to change and/or properly credit
		//code courtesy of http://www.somethinghitme.com/2013/11/13/snippets-i-always-forget-movement/
		
		// get the target x and y
	    targetX = mTarget.getX();
	    targetY = mTarget.getY();
	    x = physics.getX();
	    y = physics.getY();

	    // We need to get the distance this time around
	    tx = targetX - x;
	    ty = targetY - y;
	    dist = (float) Math.sqrt(tx * tx + ty * ty);
	    
	    /* 
	    * we calculate a velocity for our object this time around
	    * divide the target x and y by the distance and multiply it by our speed
	    * this gives us a constant movement speed.
	    */
	    
	    speed = mTarget.getPhysics().getBody().getLinearVelocity().len();//might be wrong. if so, just delete.
	    
	    velX = (tx / dist) * speed;
	    velY = (ty / dist) * speed;
	    
	    /* 
	    * Get the direction we are facing
	    * I just use -tx and -ty here because we already calculated tx and ty
	    * To get the proper x and y you need to subtract the targets x and y from 
	    * our objects x and y
	    */
	    radians = (float) Math.atan2(-ty,-tx);
	    
	    px = (float) (x - pointLength * Math.cos(radians));
	    py = (float) (y - pointLength * Math.sin(radians));

	    // Stop once we hit our target. This stops the jittery bouncing of the object. 
	    state.setState(StateComponent.STATE_IDLE);
	    if (dist > pointLength) {
	        // add our velocities
	        x += velX;
	        y += velY;
	        state.setState(StateComponent.STATE_RUNNING);
	    }
	    
	    ((PhysicsNone)physics).setPosition(x, y);
	    ((PhysicsNone)physics).setAngleRadians(radians);
		
	}
	
}

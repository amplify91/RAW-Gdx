package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;

public class PhysicsGun extends PhysicsComponent{
	
	Sprite mGunHolder;
	Vector2 mLocalGunAnchor;
	Vector2 mLocalParentAnchor;
	float mAngle = 0f;
	
	public PhysicsGun(Sprite gunHolder){
		this(gunHolder, new Vector2(0f,0f), new Vector2(0f,0f));
	}
	
	public PhysicsGun(Sprite gunHolder, Vector2 localGunAcnhor, Vector2 localParentAchor){
		mGunHolder = gunHolder;
		mLocalGunAnchor = localGunAcnhor;
		mLocalParentAnchor = localParentAchor;
	}
	
	@Override
	public void update() {
		mBody.setTransform(mGunHolder.getBody().getPosition().add(mLocalParentAnchor).sub(mLocalGunAnchor), mAngle);
		
	}
	
	public void setAngleRadians(float radians){
		mAngle = radians;
	}
	
	public void setAngleDegrees(float degrees){
		mAngle = (float)Math.toRadians(degrees);
	}
	
}

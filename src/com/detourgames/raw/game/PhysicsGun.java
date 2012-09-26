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
		this(gunHolder, new Vector2(0f,0.2f), new Vector2(-0.5f,0f));
	}
	
	public PhysicsGun(Sprite gunHolder, Vector2 localParentAchor, Vector2 localGunAcnhor){
		mGunHolder = gunHolder;
		mLocalGunAnchor = localGunAcnhor;
		mLocalParentAnchor = localParentAchor;
	}
	
	@Override
	public void update() {
		mBody.setTransform(Vector2.Zero, mAngle);
		
	}
	
	public void setAngleRadians(float radians){
		mAngle = radians;
	}
	
	public void setAngleDegrees(float degrees){
		mAngle = (float)Math.toRadians(degrees);
	}
	
	@Override
	public float getX(){
		return mGunHolder.getX()+mLocalParentAnchor.x-mLocalGunAnchor.x;
	}
	
	@Override
	public float getY(){
		return mGunHolder.getY()+mLocalParentAnchor.y-mLocalGunAnchor.y;
	}
	
}

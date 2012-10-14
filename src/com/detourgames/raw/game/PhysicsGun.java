package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.PhysicsNone;
import com.detourgames.raw.Sprite;

public class PhysicsGun extends PhysicsNone{
	
	Sprite mGunHolder;
	Vector2 mLocalGunAnchor;
	Vector2 mLocalParentAnchor;
	Vector2 mRectangularRadial;
	float mRadialLength;
	float mRadialTheta;
	
	public PhysicsGun(Sprite gunHolder){
		this(gunHolder, new Vector2(0f,0f), new Vector2(0f,0f));
	}
	
	public PhysicsGun(Sprite gunHolder, Vector2 localParentAchor, Vector2 localGunAnchor){
		super();
		mGunHolder = gunHolder;
		mLocalGunAnchor = localGunAnchor;
		mLocalParentAnchor = localParentAchor;
		mRectangularRadial = new Vector2(mXPos-localGunAnchor.x, mYPos-localGunAnchor.y);
		mRadialLength = (float)Math.sqrt((mRectangularRadial.x*mRectangularRadial.x)+(mRectangularRadial.y*mRectangularRadial.y));
		mRadialTheta = (float)Math.atan2(mRectangularRadial.y, mRectangularRadial.x);
	}
	
	public void setRotatedPosition(float radians){
		mRadialTheta = radians;
		mRectangularRadial.x = (float) (mRadialLength * Math.cos(mRadialTheta));
		mRectangularRadial.y = (float) (mRadialLength * Math.sin(mRadialTheta));
	}
	
	public void setParentAnchor(Vector2 localParentAnchor){
		mLocalParentAnchor = localParentAnchor;
	}
	
	@Override
	public void update() {
		setPosition(mGunHolder.getX()+mLocalParentAnchor.x+mRectangularRadial.x, mGunHolder.getY()+mLocalParentAnchor.y+mRectangularRadial.y);
	}
	
}

package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.PhysicsNone;
import com.detourgames.raw.Sprite;

public class PhysicsGun extends PhysicsNone{
	
	Sprite mGunHolder;
	Vector2 mLocalGunAnchor;
	Vector2 mLocalParentAnchor;
	
	public PhysicsGun(Sprite gunHolder){
		this(gunHolder, new Vector2(0f,0.2f), new Vector2(-0.5f,0f));
	}
	
	public PhysicsGun(Sprite gunHolder, Vector2 localParentAchor, Vector2 localGunAcnhor){
		super();
		mGunHolder = gunHolder;
		mLocalGunAnchor = localGunAcnhor;
		mLocalParentAnchor = localParentAchor;
	}
	
	@Override
	public void update() {
		//use vector math to change position offset by anchor points.
		setPosition(mGunHolder.getX()+mLocalParentAnchor.x-mLocalGunAnchor.x, mGunHolder.getY()+mLocalParentAnchor.y-mLocalGunAnchor.y);
	}
	
}

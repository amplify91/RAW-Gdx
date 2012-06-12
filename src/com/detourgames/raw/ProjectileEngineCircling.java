package com.detourgames.raw;

public class ProjectileEngineCircling extends ProjectileEngine{
	
	public ProjectileEngineCircling(PhysicsProjectile physicsProjectile) {
		super(physicsProjectile);
	}

	public void update()
	{
		mProjectile.mBody.applyForceToCenter(mProjectile.getDistanceVectorToPoint(mProjectile.mDestinationPoint));
	}
}

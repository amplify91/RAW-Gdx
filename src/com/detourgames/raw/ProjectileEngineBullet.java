package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;

public class ProjectileEngineBullet extends ProjectileEngine {

	private boolean hasFired=false;
	public ProjectileEngineBullet(PhysicsProjectile physicsProjectile) {
		super(physicsProjectile);
		
	}
	@Override public void update()
	{
		if(hasFired==false)
		{
		Vector2 direction=mProjectile.getDistanceVectorToPoint(mProjectile.mDestinationPoint);
		Vector2 force=direction.nor();
		mProjectile.mBody.applyForceToCenter(force.x*100,force.y*100);
		hasFired=true;
		}
	}

}

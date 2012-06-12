package com.detourgames.raw;

public abstract class ProjectileEngine {

	PhysicsProjectile mProjectile;
	
	public ProjectileEngine(PhysicsProjectile physicsProjectile)
	{
		this.mProjectile=physicsProjectile;
	}
	
	public void update()
	{
		
	}
}

package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.EventQueue;
import com.detourgames.raw.PhysicsComponent;

public class PhysicsTurret extends PhysicsComponent{
	
	@Override
	public void update(){
		
	}
	
	public void fireMissileAtTarget(Vector2 target){
		//System.out.println("Shooting at "+target.x +", "+target.y);
		EventQueue.getEventQueue().queue(new FireProjectileEvent(new Vector2(target.x,target.y), Projectile.TYPE_ENEMY, mParent));
		float radians = (float)Math.atan2(target.y-getY(), target.x-getX());
		((Turret)mParent).mBarrel.rotateTo(radians);
	}
}

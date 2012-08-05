package com.detourgames.raw.turretElement;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.EventQueue;
import com.detourgames.raw.FireProjectileEvent;
import com.detourgames.raw.PhysicsComponent;

public class TurretComponent extends PhysicsComponent{
	
	@Override
	public void update(){
		
	}
	
	public void fireMissileAtTarget(Vector2 target){
		//System.out.println("Shooting at "+target.x +", "+target.y);
		EventQueue.getEventQueue().queue(new FireProjectileEvent(new Vector2(target.x,target.y),this.mParent));
	}
}

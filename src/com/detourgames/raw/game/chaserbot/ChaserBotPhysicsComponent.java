package com.detourgames.raw.game.chaserbot;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.EventQueue;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.game.FireProjectileEvent;
import com.detourgames.raw.game.Projectile;

public class ChaserBotPhysicsComponent extends PhysicsComponent{
	
	ChaserBotController controller;
	@Override
	public void update(){
		
	}
	
	public void fireMissileAtTarget(Vector2 target){
		//System.out.println("Shooting at "+target.x +", "+target.y);
		EventQueue.getEventQueue().queue(new FireProjectileEvent(new Vector2(target.x,target.y), Projectile.TYPE_ENEMY, this.mParent));
	}
	
	public void moveTowardsTarget(Sprite target){
		EventQueue.getEventQueue().queue(new ChaserBotMoveEvent(target,this.getParentSprite()));
	}
}

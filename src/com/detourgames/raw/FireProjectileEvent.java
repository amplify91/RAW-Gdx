package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Event;

public class FireProjectileEvent extends Event {

	float x;
	float y;
	Sprite shootingSprite;

	public FireProjectileEvent(float x, float y, Sprite shootingSprite) {

		this.x = x;
		this.y = y;
		this.shootingSprite=shootingSprite;

	}
	public FireProjectileEvent(Vector2 coordinates, Sprite shootingSprite)
	{
		this.x = coordinates.x;
		this.y = coordinates.y;
		this.shootingSprite=shootingSprite;
	
	}

	@Override
	public void executeEvent() {
		//System.out.println("Shot at "+x+"' "+y);
		
		Projectile p = ProjectilePool.getProjectilePool().getProjectile();
		p.prepareForSpawn(Projectile.TYPE_RAW, shootingSprite, new Vector2(x,y));
		p.spawn(GameManager.getGameManager().getLevel().getWorld());
	}

}
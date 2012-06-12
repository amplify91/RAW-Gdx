package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Event;

public class FireProjectileEvent extends Event {

	float x;
	float y;

	public FireProjectileEvent(float x, float y) {

		this.x = x;
		this.y = y;

	}
	public FireProjectileEvent(Vector2 coordinates)
	{
		this.x = coordinates.x;
		this.y = coordinates.y;
	
	}

	@Override
	public void executeEvent() {
		System.out.println("Shot at "+x+"' "+y);
		
		Projectile p = ProjectilePool.getProjectilePool().getProjectile();
		p.prepareForSpawn(Projectile.TYPE_RAW, GameManager.getGameManager().getLevel().getHero(), new Vector2(x,y));
		p.spawn(GameManager.getGameManager().getLevel().getWorld());
	}

}
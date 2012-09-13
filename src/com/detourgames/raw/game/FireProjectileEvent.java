package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Event;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.ProjectilePool;
import com.detourgames.raw.Sprite;

public class FireProjectileEvent extends Event {

	float mX;
	float mY;
	int mType;
	Sprite mShootingSprite;

	public FireProjectileEvent(float x, float y, int type, Sprite shootingSprite){

		mX = x;
		mY = y;
		mType = type;
		mShootingSprite = shootingSprite;
		
	}
	
	public FireProjectileEvent(Vector2 coordinates, int type, Sprite shootingSprite){
		
		mX = coordinates.x;
		mY = coordinates.y;
		mType = type;
		mShootingSprite = shootingSprite;
	
	}

	@Override
	public void executeEvent() {
		
		Projectile p = ProjectilePool.getProjectilePool().getProjectile();
		p.prepareForSpawn(mType, mShootingSprite, new Vector2(mX,mY));
		p.spawn(GameManager.getGameManager().getLevel().getWorld());
		
	}

}
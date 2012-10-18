package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Event;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteFactory;

public class FireProjectileEvent extends Event {

	float mX;
	float mY;
	Vector2 mTarget;
	int mType;
	Sprite mShootingSprite;

	public FireProjectileEvent(float x, float y, int type, Sprite shootingSprite){

		mX = x;
		mY = y;
		mType = type;
		mShootingSprite = shootingSprite;
		
	}
	
	public FireProjectileEvent(Vector2 coordinates, int type, Sprite shootingSprite){
		
		mTarget = coordinates;
		mX = coordinates.x;
		mY = coordinates.y;
		mType = type;
		mShootingSprite = shootingSprite;
	
	}

	@Override
	public void executeEvent() {
		HeroProjectile p = SpriteFactory.getSpriteFactory().createHeroProjectile(mShootingSprite, mTarget);
		//p.spawn(GameManager.getGameManager().getLevel().getWorld());
		
	}

}
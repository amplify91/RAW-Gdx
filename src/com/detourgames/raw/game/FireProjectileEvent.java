package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Event;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteFactory;
import com.detourgames.raw.game.launchers.IProjectileLauncher;

public class FireProjectileEvent extends Event {

	float mX;
	float mY;
	Vector2 mDestination;
	Sprite mTarget;
	
	//int mType;
	Sprite mShootingSprite;
	IProjectileLauncher iShootingSprite;

	public FireProjectileEvent(float x, float y, Sprite shootingSprite){

		mX = x;
		mY = y;
		//mType = type;
		mShootingSprite = shootingSprite;
		
	}
	
	public FireProjectileEvent(IProjectileLauncher iShootingSprite, Sprite shootingSprite, Sprite target, Vector2 destination)
	{
		this.iShootingSprite=iShootingSprite;
		this.mTarget=target;
		this.mDestination=destination;
		if(destination==null)
			mDestination=mTarget.getPosition();
		this.mShootingSprite=shootingSprite;
	}
	
	public FireProjectileEvent(Vector2 coordinates, Sprite shootingSprite){
		
		mDestination = coordinates;
		mX = coordinates.x;
		mY = coordinates.y;
		//mType = type;
		mShootingSprite = shootingSprite;
	
	}

	@Override
	public void executeEvent() {
		if(iShootingSprite!=null)
		{
			iShootingSprite.fireProjectile(mShootingSprite, mTarget, mDestination);
			return;
		}
		if(mShootingSprite.getClass()==Hero.class){
			SpriteFactory.getSpriteFactory().createHeroProjectile(mShootingSprite, mDestination);
		}
		if(mShootingSprite.getClass()==Turret.class){
			SpriteFactory.getSpriteFactory().createTurretProjectile(mShootingSprite, mDestination);
		}
		
	}

}
package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.turretElement.TurretSprite;

public class SpriteFactory {

	Level mLevel;
	SpriteSheet mSpriteSheet;

	public SpriteFactory(Level level) {
		mLevel = level;
		mSpriteSheet = mLevel.getSpriteSheet();
		
	}

	public Hero createHero(float x, float y){
		Hero hero = new Hero(mSpriteSheet);
		hero.create(mLevel.getWorld(), x, y);
		mLevel.addDrawableSprite(hero, Level.LAYER_ACTORS_OBJECTS);
		mLevel.addUpdateableSprite(hero);
		mLevel.assignHero(hero);
		//hero.mAnimation.setAnimation(int!!!);
		
		return hero;
	}
	public TurretSprite createTurret(float x, float y){
		TurretSprite hero = new TurretSprite(mSpriteSheet);
		hero.create(mLevel.getWorld(), x, y);
		mLevel.addDrawableSprite(hero, Level.LAYER_ACTORS_OBJECTS);
		mLevel.addUpdateableSprite(hero);
		
		return hero;
	}
	
	public BackgroundTile createBackgroundTile(float x, float y, int frame, float scrollFactor){
		
		BackgroundTile bgt = new BackgroundTile();
		bgt.create(mLevel.getWorld(), x, y, mSpriteSheet, frame, scrollFactor);
		mLevel.addDrawableSprite(bgt, Level.LAYER_BACKGROUND1);
		mLevel.addUpdateableSprite(bgt);
		
		return bgt;
	}
	
	public Tile createTile(float x, float y, int frame){
		
		Tile tile = new Tile();
		
		
		tile.create(mLevel.getWorld(), x, y, frame, mSpriteSheet);
		mLevel.addDrawableSprite(tile, Level.LAYER_TERRAIN);
		
		return tile;
	}
	
	public Projectile createProjectile(){
		Projectile projectile = new Projectile(mSpriteSheet);
		projectile.create(mLevel.getWorld(), -1, -1);
		mLevel.addDrawableSprite(projectile, Level.LAYER_ACTORS_OBJECTS);
		mLevel.addUpdateableSprite(projectile);
		
		return projectile;
	}
	
	public void createHUDElements(){
		GameManager.getGameManager().getHUD().createElements(mLevel, mSpriteSheet);
	}
	
}

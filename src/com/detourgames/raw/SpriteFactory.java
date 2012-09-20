package com.detourgames.raw;

import com.detourgames.raw.game.BackgroundTile;
import com.detourgames.raw.game.Hero;
import com.detourgames.raw.game.Projectile;
import com.detourgames.raw.game.Tile;
import com.detourgames.raw.game.Turret;

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
	public Turret createTurret(float x, float y){
		Turret hero = new Turret(mSpriteSheet);
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
	
	public Sprite createSpriteFromTileNumber(float x, float y, int tileNumber, int levelLayer){
		//TODO finish this.
		if(tileNumber>=256 && tileNumber<=511){
			return createTile(x, y, tileNumber);
		}else if(tileNumber==704){
			return createHero(x, y);
		}else{
			System.out.println("Missed one! Finish SpriteFactory.createSpriteFromTileNumber or check your tile number value.");
			return null;
		}
		
	}
	
}

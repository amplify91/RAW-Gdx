package com.detourgames.raw;

import java.util.Hashtable;

import com.detourgames.raw.game.BackgroundTile;
import com.detourgames.raw.game.Hero;
import com.detourgames.raw.game.Projectile;
import com.detourgames.raw.game.Tile;
import com.detourgames.raw.game.Turret;

public class SpriteFactory {

	Level mLevel;
	SpriteSheet mSpriteSheet;
	private static Hashtable<Class,GenericPool> mPools;

	public SpriteFactory(Level level) {
		mLevel = level;
		mSpriteSheet = mLevel.getSpriteSheet();
		mPools = generatePools();
		
	}	
	
	private static Hashtable<Class,GenericPool> generatePools(){
		Hashtable<Class, GenericPool> pools = new Hashtable<Class, GenericPool>();
		pools.put(Projectile.class, new GenericPool<Projectile>());
		pools.put(Turret.class, new GenericPool<Turret>());
		pools.put(Hero.class, new GenericPool<Hero>());
		pools.put(Tile.class, new GenericPool<Tile>());
		pools.put(BackgroundTile.class, new GenericPool<BackgroundTile>());
		
		return pools;
	}

	public Hero createHero(float x, float y){
		Hero hero = (Hero)mPools.get(Hero.class).obtain();
		if(hero==null){
			hero = new Hero(mSpriteSheet, mPools.get(Hero.class));
			hero.create(mLevel.getWorld(), x, y);
		}else{
			//set propeties like, x, y, etc...
		}
		mLevel.addDrawableSprite(hero, Level.LAYER_ACTORS_OBJECTS);
		mLevel.addUpdateableSprite(hero);
		mLevel.assignHero(hero);
		
		return hero;
	}
	public Turret createTurret(float x, float y){
		Turret turret = (Turret)mPools.get(Turret.class).obtain();
		if(turret==null){
			turret = new Turret(mSpriteSheet, mPools.get(Turret.class));
			turret.create(mLevel.getWorld(), x, y);
		}else{
			//set propeties like, x, y, etc...
		}
		mLevel.addDrawableSprite(turret, Level.LAYER_ACTORS_OBJECTS);
		mLevel.addUpdateableSprite(turret);
		
		return turret;
	}
	
	public BackgroundTile createBackgroundTile(float x, float y, int frame, float scrollFactor){
		BackgroundTile bgt = (BackgroundTile)mPools.get(BackgroundTile.class).obtain();
		if(bgt==null){
			bgt = new BackgroundTile(mPools.get(BackgroundTile.class));
			bgt.create(mLevel.getWorld(), x, y, mSpriteSheet, frame, scrollFactor);
		}else{
			//set propeties like, x, y, etc...
		}
		mLevel.addDrawableSprite(bgt, Level.LAYER_BACKGROUND1);
		mLevel.addUpdateableSprite(bgt);
		
		return bgt;
	}
	
	public Tile createTile(float x, float y, int frame){
		
		Tile tile = (Tile)mPools.get(Tile.class).obtain();
		if(tile==null){
			tile = new Tile(mPools.get(Tile.class));
			tile.create(mLevel.getWorld(), x, y, frame, mSpriteSheet);
		}else{
			//set propeties like, x, y, etc...
		}
		mLevel.addDrawableSprite(tile, Level.LAYER_TERRAIN);
		
		return tile;
	}
	
	public Projectile createProjectile(){
		Projectile projectile = (Projectile)mPools.get(Projectile.class).obtain();
		if(projectile==null){
			projectile = new Projectile(mSpriteSheet, mPools.get(Projectile.class));
			projectile.create(mLevel.getWorld(), -1, -1);
		}
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
		}else if(tileNumber==755){
			return createTurret(x, y);
		}else if(tileNumber==1200){
			return createBackgroundTile(x, y, 1200, BackgroundTile.BACKGROUND1_SCROLL_FACTOR);
		}else{
			System.out.println("Missed one! Finish SpriteFactory.createSpriteFromTileNumber or check your tile number value.");
			return null;
		}
		
	}
	

	
}

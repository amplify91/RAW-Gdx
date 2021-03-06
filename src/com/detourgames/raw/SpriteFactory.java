package com.detourgames.raw;

import java.util.Hashtable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.game.BackgroundTile;
import com.detourgames.raw.game.GFXSprite;
import com.detourgames.raw.game.Hero;
import com.detourgames.raw.game.HeroProjectile;
import com.detourgames.raw.game.Tile;
import com.detourgames.raw.game.Turret;
import com.detourgames.raw.game.TurretProjectile;
import com.detourgames.raw.game.launchers.HomingRocketLauncher;
import com.detourgames.raw.game.launchers.SimpleRocketLauncher;

public class SpriteFactory {

	private static Level mLevel;
	static SpriteSheet mSpriteSheet;
	public static Hashtable<Class<? extends Recyclable>,GenericPool<? extends Recyclable>> mPools;
	private static final SpriteFactory mSpriteFactory = new SpriteFactory();
	private SpriteFactory(){
		mPools = generatePools();
	}

	public static SpriteFactory getSpriteFactory(){
		return mSpriteFactory;
	}
	
	public Level getLevel(){
		return mLevel;
	}
	
	public void setLevel(Level level){
		mLevel = level;
		mSpriteSheet = mLevel.getSpriteSheet();
	}
	
	private static Hashtable<Class<? extends Recyclable>,GenericPool<? extends Recyclable>> generatePools(){//"extends" here is treated as "implements" since Recyclable.java is an interface.
		Hashtable<Class<? extends Recyclable>, GenericPool<? extends Recyclable>> pools = new Hashtable<Class<? extends Recyclable>, GenericPool<? extends Recyclable>>();
		pools.put(HeroProjectile.class, new GenericPool<HeroProjectile>());
		pools.put(Turret.class, new GenericPool<Turret>());
		pools.put(Hero.class, new GenericPool<Hero>());
		pools.put(Tile.class, new GenericPool<Tile>());
		pools.put(BackgroundTile.class, new GenericPool<BackgroundTile>());
		pools.put(TurretProjectile.class,new GenericPool<TurretProjectile>());
		pools.put(GFXSprite.class,new GenericPool<GFXSprite>());
		pools.put(Projectile.class, new GenericPool<Projectile>());
		
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
	
	public Turret createSimpleTurret(float x, float y){
		Turret turret = (Turret)mPools.get(Turret.class).obtain();
		if(turret==null){
			turret = new Turret(mSpriteSheet, mPools.get(Turret.class));
			turret.create(mLevel.getWorld(), x, y);
		}
		//turret.SetLauncher(SimpleRocketLauncher.getSimpleRocketLauncher());
		mLevel.addDrawableSprite(turret, Level.LAYER_ACTORS_OBJECTS);
		mLevel.addUpdateableSprite(turret);
		
		return turret;
	}
	
	public Turret createHomingTurret(float x, float y){
		Turret t = createSimpleTurret(x,y);
		//t.SetLauncher(HomingRocketLauncher.getHomingRocketLauncher());
		return t;
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
	
	public HeroProjectile createHeroProjectile(Sprite parent, Vector2 destination){
		HeroProjectile projectile = (HeroProjectile)mPools.get(HeroProjectile.class).obtain();
		if(projectile==null){
			//System.out.println("Create new.");
			projectile = new HeroProjectile(mSpriteSheet, mPools.get(HeroProjectile.class));
			projectile.create(mLevel.getWorld(), parent, destination);
		}else{
			//System.out.println("Use old.");
			projectile.create(mLevel.getWorld(), parent, destination);
		}
		mLevel.addDrawableSprite(projectile, Level.LAYER_ACTORS_OBJECTS);
		mLevel.addUpdateableSprite(projectile);
		
		return projectile;
	}
	
	public Projectile createHomingProjectile(Sprite parent, Sprite target){
		Projectile projectile = createTurretProjectile(parent,target.getPosition());
		projectile.setController(new ControllerHoming(target,100));
		return projectile;
	}
	
	public TurretProjectile createTurretProjectile(Sprite parent, Vector2 destination){
		TurretProjectile projectile = (TurretProjectile)mPools.get(TurretProjectile.class).obtain();
		if(projectile==null){
			//System.out.println("Create new.");
			projectile = new TurretProjectile(mSpriteSheet, mPools.get(TurretProjectile.class));
			projectile.create(mLevel.getWorld(), parent, destination);
		}else{
			//System.out.println("Use old.");
			projectile.create(mLevel.getWorld(), parent, destination);
		}
		mLevel.addDrawableSprite(projectile, Level.LAYER_ACTORS_OBJECTS);
		mLevel.addUpdateableSprite(projectile);
		
		return projectile;
	}
	
	public GFXSprite createGFX(float width, float height, float angle, Vector2 position, Animation animation, Animation finalFrame){
		GFXSprite gfx = (GFXSprite)mPools.get(GFXSprite.class).obtain();
		
		if(gfx==null){
			gfx = new GFXSprite(mSpriteSheet, mPools.get(GFXSprite.class));
			gfx.create(width, height, angle, position, animation, finalFrame);
		}else{
			gfx.create(width, height, angle, position, animation, finalFrame);
		}
		mLevel.addDrawableSprite(gfx, Level.LAYER_OVER_GFX);
		mLevel.addUpdateableSprite(gfx);
		
		return null;
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
			return createSimpleTurret(x, y);
		}else if(tileNumber==1200){
			return createBackgroundTile(x, y, 1200, BackgroundTile.BACKGROUND1_SCROLL_FACTOR);
		}else{
			System.out.println("Missed one! Finish SpriteFactory.createSpriteFromTileNumber or check your tile number value.");
			return null;
		}
		
	}
	

	
}

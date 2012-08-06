package com.detourgames.raw;

import java.util.ArrayList;

import com.detourgames.raw.game.Projectile;

public class ProjectilePool {
	
	private static ProjectilePool mProjectilePool = new ProjectilePool();
	
	private static ArrayList<Projectile> mProjectiles = new ArrayList<Projectile>();//TODO change to Array?
	
	private static Level mLevel;
	private static SpriteFactory mSpriteFactory;
	
	private ProjectilePool(){
		
	}
	
	public static ProjectilePool getProjectilePool(){
		mLevel = GameManager.getGameManager().getLevel();
		mSpriteFactory = new SpriteFactory(mLevel);
		return mProjectilePool;
	}
	
	public Projectile getProjectile(){
		for(int i=0;i<mProjectiles.size();i++){
			if(!mProjectiles.get(i).isActive){
				//System.out.println("Number of total Projectiles in pool: " + mProjectiles.size() + ". Used an old one!");
				return mProjectiles.get(i);
			}
		}
		//System.out.println("Number of total Projectiles in pool: " + mProjectiles.size() + ". Made a new one!");
		Projectile p = mSpriteFactory.createProjectile();
		mProjectiles.add(p);
		
		getProjectile();
		return p;
		
	}
	
}

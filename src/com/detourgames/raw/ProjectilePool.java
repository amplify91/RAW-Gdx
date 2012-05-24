package com.detourgames.raw;

import java.util.ArrayList;

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
				return mProjectiles.get(i);
			}
		}
		
		return mSpriteFactory.createProjectile();
		
	}
	
}

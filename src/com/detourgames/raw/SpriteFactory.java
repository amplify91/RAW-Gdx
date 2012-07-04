package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class SpriteFactory {

	Level mLevel;
	SpriteSheet mSpriteSheet;
	
	private static final Vector2[] verts = new Vector2[]{new Vector2(0f,0f),new Vector2(0.5f,0f),new Vector2(0.5f,0.5f),new Vector2(0f,0.5f)};

	public SpriteFactory(Level level) {
		mLevel = level;
		mSpriteSheet = mLevel.getSpriteSheet();
		
	}

	public Hero createHero(float x, float y){
		Hero hero = new Hero(mSpriteSheet);
		hero.create(mLevel.getWorld(), x, y);
		mLevel.addDrawableSprite(hero);
		mLevel.addUpdateableSprite(hero);
		mLevel.assignHero(hero);
		//hero.mAnimation.setAnimation(int!!!);
		
		return hero;
	}
	
	public Tile createTile(float x, float y, int shape, int frame){
		
		Tile tile = new Tile();
		Vector2[] tileVerts = verts;
		
		if(shape==1){
			tileVerts = verts;
		}
		
		Animation animation = AnimationComponent.createAnimation(mSpriteSheet, new int[]{frame});
		tile.create(mLevel.getWorld(), x, y, animation, tileVerts);
		mLevel.addDrawableSprite(tile);
		
		return tile;
	}
	
	public Projectile createProjectile(){
		Projectile projectile = new Projectile(mSpriteSheet);
		projectile.create(mLevel.getWorld(), -1, -1);
		mLevel.addDrawableSprite(projectile);
		mLevel.addUpdateableSprite(projectile);
		
		return projectile;
	}

}

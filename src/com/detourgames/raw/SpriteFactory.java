package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;

public class SpriteFactory {

	Level mLevel;
	
	private static final Vector2[] verts = new Vector2[]{new Vector2(0f,0f),new Vector2(0.5f,0f),new Vector2(0.5f,0.5f),new Vector2(0f,0.5f)};

	public SpriteFactory(Level level) {
		mLevel = level;
		
	}

	/*public Hero createHero(float x, float y){
		Hero hero = new Hero();
		hero.create(mLevel.getWorld(), x, y);
		mLevel.addDrawableSprite(hero);
		mLevel.addUpdateableSprite(hero);
		mLevel.assignHero(hero);
		
		return hero;
	}*/
	
	public Tile createTile(float x, float y, int shape, Animation animation){
		
		Tile tile = new Tile();
		Vector2[] tileVerts = verts;
		
		if(shape==1){
			tileVerts = verts;
		}
		
		tile.create(mLevel.getWorld(), x, y, animation, tileVerts);
		tile.pauseAnimation();
		mLevel.addDrawableSprite(tile);
		
		return tile;
	}
	
	/*public Projectile createProjectile(){
		Projectile projectile = new Projectile();
		projectile.create(mLevel.getWorld(), 0, 0);
		mLevel.addDrawableSprite(projectile);
		mLevel.addUpdateableSprite(projectile);
		
		return projectile;
	}*/

}

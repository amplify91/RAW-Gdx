package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Projectile extends Sprite implements IReusable{
	
	public boolean isActive = false;
	public boolean isReadyForSpawn = false;
	
	public static final int TYPE_RAW = 1;
	public static final int TYPE_ENEMY=2;
	
	public static final float WIDTH_RAW = 0.5f;
	public static final float HEIGHT_RAW = 0.5f;
	
	public static final float VELOCITY_RAW = 1.0f;
	
	public static final Vector2[] VERTS_RAW = new Vector2[]{new Vector2(0.0f,0.0f), new Vector2(0.2f,0.0f), new Vector2(0.2f,0.2f), new Vector2(0.0f,0.2f)};
	
	
	public Projectile(SpriteSheet spriteSheet){
		
		super(new PhysicsProjectile(), new AnimationStatic(), new StateStatic());
		Animation[] animations = new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, new int[]{20})
				//more animations,
				//more animations
				};
		mAnimation.setAnimations(animations);
		
	}
	
	public void create(World world, float x, float y){
		//TODO
		mDrawWidth = WIDTH_RAW;
		mDrawHeight = HEIGHT_RAW;
		mDrawOffsetX = -WIDTH_RAW / 2f;
		mDrawOffsetY = -HEIGHT_RAW / 2f;
		mPhysics.create(world, x, y, VERTS_RAW, true, FixtureType.HERO_PROJECTILE);//TODO change to true for dynamic projectiles.
		mPhysics.mBody.setBullet(true);
	}
	
	public void prepareForSpawn(int type, Sprite parent, Vector2 destination){
		((PhysicsProjectile)mPhysics).setProjectileProperties(type, parent, destination);
		//TODO finish
		
		isReadyForSpawn = true;
	}

	public void spawn(World world) {
		if(isReadyForSpawn){
			isActive = true;
			isReadyForSpawn = false;
		}else{
			//Log.e("Projectile", "prepareForSpawn() before spawn()");
		}
	}

	public void recycle() {
		isActive = false;
		isReadyForSpawn = false;
		//TODO set to a neutral state that won't interfere with the rest of the game.
	}
	
}

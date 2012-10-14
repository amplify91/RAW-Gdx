package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.GenericPool;
import com.detourgames.raw.IReusable;
import com.detourgames.raw.ProjectilePool;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteFactory;
import com.detourgames.raw.SpriteSheet;

public class Projectile extends Sprite implements IReusable{
	
	public boolean isActive = false;
	public boolean isReadyForSpawn = false;
	
	public static final int TYPE_RAW = 1;
	public static final int TYPE_ENEMY = 2;
	
	public static final float WIDTH_RAW = 0.4f;
	public static final float HEIGHT_RAW = 0.4f;
	
	public static final float VELOCITY_RAW = 20.0f;
	
	public static final int DAMAGE_RAW = 100;
	public static final int DAMAGE_TURRET = 20;
	
	public static final Vector2[] VERTS_RAW = new Vector2[]{new Vector2(-0.2f,-0.2f), new Vector2(0.2f,-0.2f), new Vector2(0.2f,0.2f), new Vector2(-0.2f,0.2f)};
	
	
	public Projectile(SpriteSheet spriteSheet, GenericPool<Projectile> pool){
		
		super(new PhysicsProjectile(), new AnimationProjectile(spriteSheet,WIDTH_RAW,HEIGHT_RAW), new StateProjectile(), new ControllerComponent());
		mPool = pool;
		
	}
	
	public void create(World world, float x, float y){
		//TODO
		mPhysics.create(world, x, y, VERTS_RAW, true, FixtureType.HERO_PROJECTILE);
		mPhysics.getBody().setBullet(true);
	}
	
	public void prepareForSpawn(int type, Sprite parent, Vector2 destination){
		mState.setInitialState();
		((PhysicsProjectile)mPhysics).setProjectileProperties(type, parent, destination);
		//TODO finish
		
		isReadyForSpawn = true;
	}

	public void spawn(World world) {
		if(isReadyForSpawn){
			isActive = true;
			isReadyForSpawn = false;
			mPhysics.getBody().setActive(true);//this is the call that sets them going.
		}else{
			System.out.println("call prepareForSpawn() before spawn()");
		}
	}

	public void recycle() {
		isActive = false;
		isReadyForSpawn = false;
		mPhysics.die();
		mPhysics.getBody().setTransform(ProjectilePool.POOL_LOCATION.x, ProjectilePool.POOL_LOCATION.y, 0);
		//mPhysics.getBody().setLinearVelocity(0, 0);
		mPhysics.getBody().setActive(false);
		//TODO set to a neutral state that won't interfere with the rest of the game.
	}
	
}

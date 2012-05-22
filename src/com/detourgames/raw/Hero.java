package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Hero extends Sprite{
	
	Vector2 running;
	private static final float MAX_SPEED = 10;
	private static final float HEIGHT = 1;
	private static final float WIDTH = 1;
	
	public Hero(){
		super(new PhysicsHero(), new AnimationComponent());
		running = new Vector2(25, 0);
		//mAnimation.setAnimation(Animation.ANIMATION_RUNNING);
		
	}
	
	public void create(World world, float x, float y){
		mDrawWidth = WIDTH;
		mDrawHeight = HEIGHT;
		mDrawOffsetX = -WIDTH / 2f;
		mDrawOffsetY = -HEIGHT / 2f;
		mPhysics.create(world, x, y, WIDTH, HEIGHT, true);
		//mAnimation.setAnimation(Animation.RUNNING);
	}
	
	public void jump(){
		((PhysicsHero) mPhysics).jump();
	}
	
	public void dash(){
		((PhysicsHero) mPhysics).dash();
	}
	
}

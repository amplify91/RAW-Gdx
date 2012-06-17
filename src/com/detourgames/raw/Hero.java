package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Hero extends Sprite{
	
	Vector2 running;
	private static final float MAX_SPEED = 10;
	private static final float HEIGHT = 1;
	private static final float WIDTH = 1;
	//hero animations/flags
	public static final int RUNNING = 0;
	public static final int JUMPING = 1;
	public static final int[] ANIMATION_RUNNING = {0,1,2,3,4,5,6,7};
	public static final int[] ANIMATION_JUMPING = {8,9,10,11,12,13,14,15};
	
	public Hero(SpriteSheet spriteSheet){
		super(new PhysicsHero(), new AnimationComponent(), new StateHero());
		Animation[] animations = new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_RUNNING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_JUMPING)
				//more animations,
				//more animations,
				//more animations
				};
		mAnimation.setAnimations(animations);
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
		//mAnimation.setAnimation(JUMPING);
	}
	
	public void dash(){
		((PhysicsHero) mPhysics).dash();
	}
	
}

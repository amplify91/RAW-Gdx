package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Hero extends Sprite{
	
	Vector2 running;
	private static final float MAX_SPEED = 10;
	private static final float HEIGHT = 1;
	private static final float WIDTH = 1;
	
	public Hero(SpriteSheet spriteSheet){
		super(new PhysicsHero(), new AnimationHero(spriteSheet), new StateHero());
		running = new Vector2(25, 0);
		//mAnimation.setAnimation(Animation.ANIMATION_RUNNING);
		
	}
	
	public void create(World world, float x, float y){
		mDrawWidth = WIDTH;
		mDrawHeight = HEIGHT;
		mDrawOffsetX = -WIDTH / 2f;
		mDrawOffsetY = -HEIGHT / 2f;
		mPhysics.create(world, x, y, WIDTH, HEIGHT, true);
	}
	
	public void jump(){
		((PhysicsHero) mPhysics).jump();
		mState.setState(StateHero.STATE_JUMPING);
	}
	
	public void dash(){
		((PhysicsHero) mPhysics).dash();
		//mState.setState(StateHero.STATE_DASHING);
	}
	
}

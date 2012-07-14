package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.World;

public class BackgroundTile extends Sprite{
	
	public static final float WIDTH = 12.5f;
	public static final float HEIGHT = 7.5f;
	
	public BackgroundTile() {
		super(new PhysicsScrolling(GameManager.getGameManager().getLevel().getHero()), new AnimationStatic(), new StateNone());
		// might need to be created AFTER Hero.
	}
	
	public void create(World world, float x, float y, SpriteSheet spriteSheet, int frame, float scrollSpeed){
		mAnimation.setAnimation(AnimationComponent.createAnimation(spriteSheet, new int[]{frame}));
		mAnimation.setSize(WIDTH, HEIGHT);
		((PhysicsScrolling)mPhysics).create(world, x, y, WIDTH, HEIGHT);
		((PhysicsScrolling)mPhysics).setScrollSpeed(scrollSpeed);
		((PhysicsScrolling)mPhysics).setFocus(GameManager.getGameManager().getLevel().getHero());
	}
	
}

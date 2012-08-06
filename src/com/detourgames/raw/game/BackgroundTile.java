package com.detourgames.raw.game;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.AnimationStatic;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.PhysicsScrolling;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateNone;

public class BackgroundTile extends Sprite{
	
	public static final float WIDTH = 12.5f;
	public static final float HEIGHT = 7.5f;
	public static final float BACKGROUND1_SCROLL_FACTOR = 0.9f;
	public static final float BACKGROUND2_SCROLL_FACTOR = 0.5f;
	public static final float BACKGROUND3_SCROLL_FACTOR = 0.1f;
	
	public BackgroundTile() {
		super(new PhysicsScrolling(GameManager.getGameManager().getLevel().getHero()), new AnimationStatic(), new StateNone(), new ControllerComponent());
		// might need to be created AFTER Hero.
	}
	
	public void create(World world, float x, float y, SpriteSheet spriteSheet, int frame, float scrollFactor){
		mAnimation.setAnimation(AnimationComponent.createAnimation(spriteSheet, new int[]{frame}));
		mAnimation.setSize(WIDTH, HEIGHT);
		((PhysicsScrolling)mPhysics).create(world, x, y, WIDTH, HEIGHT);
		((PhysicsScrolling)mPhysics).setScrollFactor(scrollFactor);
		((PhysicsScrolling)mPhysics).setFocus(GameManager.getGameManager().getLevel().getHero());
	}
	
}
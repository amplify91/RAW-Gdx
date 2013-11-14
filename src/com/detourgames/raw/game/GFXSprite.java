package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.ControllerNone;
import com.detourgames.raw.GenericPool;
import com.detourgames.raw.PhysicsNone;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class GFXSprite extends Sprite{

	public GFXSprite(SpriteSheet spriteSheet, GenericPool<?> pool) {
		super(new PhysicsNone(), new AnimationOneTime(), new StateOneTimeGraphic(), new ControllerNone(), pool);
		
	}
	
	public void create(float width, float height, float angle, Vector2 position, Animation animation, Animation finalFrame){
		
		mAnimation.setWidth(width);
		mAnimation.setHeight(height);
		mAnimation.setAnimations(new Animation[]{animation, finalFrame});
		mAnimation.setTransitionAnimation(0, 1);
		((PhysicsNone)mPhysics).setAngleRadians(angle);
		((PhysicsNone)mPhysics).setPosition(position.x, position.y);
	}

}

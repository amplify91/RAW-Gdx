package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationNone extends AnimationComponent{
	
	//Aimation component for Sprites that are not visible at all.
	
	public AnimationNone(SpriteSheet spriteSheet){
		super(new Animation[]{AnimationComponent.createAnimation(spriteSheet, new int[]{0})},0,0);
	}
	
	@Override
	public void update(StateComponent state) {
		// do nothing.
		
	}

}

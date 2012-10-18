package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateComponent;

public class AnimationTurretProjectile extends AnimationComponent{
	
	public static final int INDEX_IDLE = 0;
	public static final int[] ANIMATION_IDLE = {517};
	
	public AnimationTurretProjectile(SpriteSheet spriteSheet, float width, float height){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_IDLE)
				}, width, height);
	}
	
	@Override
	public void update(StateComponent state) {
		// TODO Auto-generated method stub
		
	}

}

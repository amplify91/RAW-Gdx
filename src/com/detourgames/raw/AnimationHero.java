package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationHero extends AnimationComponent{
	
	public static final int INDEX_RUNNING = 0;
	public static final int INDEX_JUMPING = 1;
	public static final int[] ANIMATION_RUNNING = {0,1,2,3,4,5,6,7};
	public static final int[] ANIMATION_JUMPING = {8,9,10,11,12,13,14,15};
	
	public AnimationHero(SpriteSheet spriteSheet){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_RUNNING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_JUMPING)
				//more animations,
				//more animations,
				//more animations
				});
	}
	
	@Override
	public void update(StateComponent state) {
		
		if(state.getState()==StateHero.STATE_RUNNING && mCurrentAnimation!=INDEX_RUNNING){
			setAnimation(INDEX_RUNNING);
		}else if(state.getState()==StateHero.STATE_JUMPING && mCurrentAnimation!=INDEX_JUMPING){
			setAnimation(INDEX_JUMPING);
		}
		
	}
	
}

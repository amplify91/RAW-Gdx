package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationButton extends AnimationComponent{
	
	public static final int INDEX_JUMP_UP = 0;
	public static final int INDEX_JUMP_DOWN = 1;
	public static final int INDEX_DOUBLE_JUMP_UP = 2;
	public static final int INDEX_DOUBLE_JUMP_DOWN = 3;
	public static final int INDEX_DASH_UP = 4;
	public static final int INDEX_DASH_DOWN = 5;
	public static final int INDEX_DASH_UNAVAILABLE = 6;
	public static final int[] ANIMATION_JUMP_UP = {768};
	public static final int[] ANIMATION_JUMP_DOWN = {769};
	public static final int[] ANIMATION_DOUBLE_JUMP_UP = {770};
	public static final int[] ANIMATION_DOUBLE_JUMP_DOWN = {771};
	public static final int[] ANIMATION_DASH_UP = {512};
	public static final int[] ANIMATION_DASH_DOWN = {513};
	public static final int[] ANIMATION_DASH_UNAVAILABLE = {514};
	
	public AnimationButton(SpriteSheet spriteSheet){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_JUMP_UP),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_JUMP_DOWN),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_DOUBLE_JUMP_UP),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_DOUBLE_JUMP_DOWN),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_DASH_UP),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_DASH_DOWN),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_DASH_UNAVAILABLE)
		},0f,0f);
	}

	@Override
	public void update(StateComponent state) {
		if(mCurrentAnimation!=INDEX_JUMP_UP && state.getState()==StateButton.STATE_JUMP_UP){
			setAnimation(INDEX_JUMP_UP);
		}else if(mCurrentAnimation!=INDEX_JUMP_DOWN && state.getState()==StateButton.STATE_JUMP_DOWN){
			setAnimation(INDEX_JUMP_DOWN);
		}else if(mCurrentAnimation!=INDEX_DASH_UP && state.getState()==StateButton.STATE_DASH_UP){
			setAnimation(INDEX_DASH_UP);
		}else if(mCurrentAnimation!=INDEX_DASH_DOWN && state.getState()==StateButton.STATE_DASH_DOWN){
			setAnimation(INDEX_DASH_DOWN);
		}else if(mCurrentAnimation!=INDEX_DASH_UNAVAILABLE && state.getState()==StateButton.STATE_DASH_UP){
			setAnimation(INDEX_DASH_UP);
		}
	}

	@Override
	public Animation[] getDeathAnimation() {
		return null;
	}
	
}

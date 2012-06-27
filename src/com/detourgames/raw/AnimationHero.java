package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationHero extends AnimationComponent{
	
	public static final int INDEX_RUNNING = 0;
	public static final int INDEX_PRE_JUMPING = 1;
	public static final int INDEX_JUMPING = 2;
	public static final int INDEX_PRE_FALLING = 3;
	public static final int INDEX_FALLING = 4;
	public static final int INDEX_LANDING = 5;
	public static final int[] ANIMATION_RUNNING = {0,1,2,3,4,5,6,7};
	public static final int[] ANIMATION_PRE_JUMPING = {8,9,10,11};
	public static final int[] ANIMATION_JUMPING = {12};
	public static final int[] ANIMATION_PRE_FALLING = {13};
	public static final int[] ANIMATION_FALLING = {14};
	public static final int[] ANIMATION_LANDING = {15};
	
	public AnimationHero(SpriteSheet spriteSheet){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_RUNNING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_PRE_JUMPING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_JUMPING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_PRE_FALLING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_FALLING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_LANDING)
				//more animations,
				//more animations,
				//more animations
				});
	}
	
	@Override
	public void update(StateComponent state) {
		
		if(state.getState()==StateHero.STATE_RUNNING && mCurrentAnimation!=INDEX_LANDING && mCurrentAnimation!=INDEX_RUNNING){
			setAnimation(INDEX_LANDING);
		}else if(state.getState()==StateHero.STATE_JUMPING && mCurrentAnimation!=INDEX_JUMPING && mCurrentAnimation!=INDEX_PRE_JUMPING){
			setAnimation(INDEX_PRE_JUMPING);
		}else if(state.getState()==StateHero.STATE_FALLING && mCurrentAnimation!=INDEX_FALLING && mCurrentAnimation!=INDEX_PRE_FALLING){
			setAnimation(INDEX_PRE_FALLING);
		}
		if(mCurrentAnimation==INDEX_LANDING && state.getState()==StateHero.STATE_RUNNING){
			if(isAnimationFinished()){
				setAnimation(INDEX_RUNNING);
			}
		}
		if(mCurrentAnimation==INDEX_PRE_JUMPING && state.getState()==StateHero.STATE_JUMPING){
			if(isAnimationFinished()){
				setAnimation(INDEX_JUMPING);
			}
		}
		if(mCurrentAnimation==INDEX_PRE_FALLING && state.getState()==StateHero.STATE_FALLING){
			if(isAnimationFinished()){
				setAnimation(INDEX_FALLING);
			}
		}
		
	}
	
}

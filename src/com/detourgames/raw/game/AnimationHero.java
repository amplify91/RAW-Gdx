package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateComponent;

public class AnimationHero extends AnimationComponent{
	
	public static final int INDEX_RUNNING = 0;
	public static final int INDEX_PRE_JUMPING = 1;
	public static final int INDEX_JUMPING = 2;
	public static final int INDEX_PRE_FALLING = 3;
	public static final int INDEX_FALLING = 4;
	public static final int INDEX_LANDING = 5;
	public static final int INDEX_DASHING = 6;
	public static final int INDEX_HURT = 7;
	public static final int INDEX_HURT_FF = 8;
	public static final int[] ANIMATION_RUNNING = {704,705,706,707,708,709,710,711};
	public static final int[] ANIMATION_PRE_JUMPING = {712,713,714,715};
	public static final int[] ANIMATION_JUMPING = {716};
	public static final int[] ANIMATION_PRE_FALLING = {717};
	public static final int[] ANIMATION_FALLING = {718};
	public static final int[] ANIMATION_LANDING = {719,720};
	public static final int[] ANIMATION_DASHING = {722,723,724,725,726,727};
	public static final int[] ANIMATION_HURT = {736,737,738,739};
	public static final int[] ANIMATION_HURT_FF = {739};
	
	public AnimationHero(SpriteSheet spriteSheet, float width, float height){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_RUNNING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_PRE_JUMPING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_JUMPING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_PRE_FALLING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_FALLING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_LANDING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_DASHING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_HURT),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_HURT_FF)
				//more animations,
				//more animations,
				//more animations
				}, width, height);
	}
	
	@Override
	public void update(StateComponent state) {
		
		if(state.getState()==StateHero.STATE_RUNNING && mCurrentAnimation!=INDEX_LANDING && mCurrentAnimation!=INDEX_RUNNING){
			setTransitionAnimation(INDEX_LANDING, INDEX_RUNNING);
		}else if(state.getState()==StateHero.STATE_JUMPING && mCurrentAnimation!=INDEX_JUMPING && mCurrentAnimation!=INDEX_PRE_JUMPING){
			setTransitionAnimation(INDEX_PRE_JUMPING, INDEX_JUMPING);
		}else if(state.getState()==StateHero.STATE_FALLING && mCurrentAnimation!=INDEX_FALLING && mCurrentAnimation!=INDEX_PRE_FALLING){
			setTransitionAnimation(INDEX_PRE_FALLING, INDEX_FALLING);
		}
		
	}

	@Override
	public Animation[] getDeathAnimation() {
		return new Animation[]{getAnimation(INDEX_HURT), getAnimation(INDEX_HURT_FF)};
	}
	
}

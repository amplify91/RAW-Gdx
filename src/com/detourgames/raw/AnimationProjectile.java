package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationProjectile extends AnimationComponent{
	
	public static final int INDEX_IDLE = 0;
	public static final int INDEX_HURTING = 1;
	public static final int[] ANIMATION_IDLE = {20};
	public static final int[] ANIMATION_HURTING = {21,22,23};
	
	public AnimationProjectile(SpriteSheet spriteSheet){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_IDLE),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_HURTING)
				});
	}
	
	@Override
	public void update(StateComponent state) {
		if(state.getState()==StateProjectile.STATE_IDLE && mCurrentAnimation!=INDEX_IDLE){
			setAnimation(INDEX_IDLE);
		}
		if(state.getState()==StateProjectile.STATE_HURTING && mCurrentAnimation!=INDEX_HURTING){
			setAnimation(INDEX_HURTING);
		}
		if(state.getState()==StateProjectile.STATE_HURTING && isAnimationFinished()){
			state.setState(StateProjectile.STATE_DEAD);
		}
	}
}

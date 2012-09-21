package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateComponent;

public class AnimationTurret extends AnimationComponent{
	
	public static final int INDEX_IDLE = 0;
	public static final int[] ANIMATION_IDLE = {755,756};
	
	public AnimationTurret(SpriteSheet spriteSheet, float width, float height){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_IDLE)
				//more animations,
				//more animations,
				//more animations
				}, width, height);
	}
	
	@Override
	public void update(StateComponent state) {
		if(state.getState()==StateTurret.STATE_IDLE && mCurrentAnimation!=INDEX_IDLE){
			setAnimation(1);
		}
	}
	
}

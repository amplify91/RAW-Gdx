package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateComponent;

public class AnimationTurret extends AnimationComponent{
	
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
	
	public AnimationTurret(SpriteSheet spriteSheet, float width, float height){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_PRE_JUMPING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_JUMPING)
				//more animations,
				//more animations,
				//more animations
				}, width, height);
	}
	
	@Override
	public void update(StateComponent state) {
		if(state.getState()==StateTurret.STATE_IDLE)
			setAnimation(1);
		else if(state.getState()==StateTurret.STATE_SHOOTING)
			setTransitionAnimation(0,1);
		
	}
	
}

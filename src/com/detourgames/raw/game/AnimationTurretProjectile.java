package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateComponent;

public class AnimationTurretProjectile extends AnimationComponent{
	
	public static final int INDEX_IDLE = 0;
	public static final int INDEX_EXPLODING = 1;
	public static final int INDEX_EXPLODING_FF = 2;
	public static final int[] ANIMATION_IDLE = {517};
	public static final int[] ANIMATION_EXPLODING = {519,520,521};
	public static final int[] ANIMATION_EXPLODING_FF = {521};
	
	public AnimationTurretProjectile(SpriteSheet spriteSheet, float width, float height){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_IDLE),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_EXPLODING),
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_EXPLODING_FF)
				}, width, height);
	}
	
	@Override
	public void update(StateComponent state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Animation[] getDeathAnimation() {
		return new Animation[]{getAnimation(INDEX_EXPLODING), getAnimation(INDEX_EXPLODING_FF)};
	}

}

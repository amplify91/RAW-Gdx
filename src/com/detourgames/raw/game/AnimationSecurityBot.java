package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.StateComponent;

public class AnimationSecurityBot extends AnimationComponent{
	
	public static final int INDEX_FLYING = 0;
	public static final int[] ANIMATION_FLYING = {759,760};
	public static final int[] ANIMATION_SEEKING = {761,762};
	public static final int[] ANIMATION_ATTACKING = {763,764,765};
	public static final int[] ANIMATION_DYING = {766};
	
	@Override
	public void update(StateComponent state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Animation[] getDeathAnimation() {
		// TODO Auto-generated method stub
		return null;
	}

}

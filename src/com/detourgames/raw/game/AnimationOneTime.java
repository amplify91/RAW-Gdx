package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.StateComponent;

public class AnimationOneTime extends AnimationComponent{
	
	public AnimationOneTime(Animation animation, Animation lastFrame, float width, float height){
		super(new Animation[]{animation, lastFrame}, width, height);
		setTransitionAnimation(0, 1);
	}
	
	public AnimationOneTime(){
		super();
	}
	
	@Override
	public void update(StateComponent state) {
		
	}

	@Override
	public Animation[] getDeathAnimation() {
		return null;
	}

}

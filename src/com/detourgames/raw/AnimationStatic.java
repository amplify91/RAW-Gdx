package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationStatic extends AnimationComponent{
	
	//For static images that only display one frame and never change. Example: Backgrounds.
	
	public AnimationStatic(){
		
	}
	
	public AnimationStatic(Animation animation, float width, float height){
		super(new Animation[]{animation}, width, height);
	}
	
	@Override
	public void update(StateComponent state) {
		// do nothing.
	}
	
}

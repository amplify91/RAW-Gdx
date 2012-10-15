package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateComponent;

public class AnimationTurretBarrel extends AnimationComponent{
	
	public static final int INDEX_SHOOTING = 0;
	public static final int[] ANIMATION_SHOOTING = {757};
	
	public static final float WIDTH = Turret.WIDTH/2f;
	public static final float HEIGHT = Turret.HEIGHT/2f;
	
	public AnimationTurretBarrel(SpriteSheet spriteSheet){
		super(new Animation[]{
				AnimationComponent.createAnimation(spriteSheet, ANIMATION_SHOOTING)
		} ,WIDTH, HEIGHT);
	}
	
	@Override
	public void update(StateComponent state) {
		
	}
	
}

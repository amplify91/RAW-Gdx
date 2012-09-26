package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.ControllerNone;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class HeroArm extends Sprite{

	public HeroArm(SpriteSheet spriteSheet, Sprite hero) {
		super(new PhysicsGun(hero), new AnimationHeroArm(spriteSheet), new StateHeroArm(), new ControllerNone());
	}
	
	//TODO move this shoot() method back into hero.
	public void shoot(float x, float y){
		float angle = (float)Math.atan2(y-mPhysics.getY(), x-mPhysics.getX());
		((PhysicsGun)mPhysics).setAngleRadians(angle);
	}
	
	public void shoot(Vector2 target){
		target = target.sub(mPhysics.getPosition());
		float angle = (float)Math.atan2(target.y, target.x);
		((PhysicsGun)mPhysics).setAngleRadians(angle);
	}
	
}

package com.detourgames.raw.game;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.ControllerNone;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.Level;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class HeroArm extends Sprite{

	public HeroArm(SpriteSheet spriteSheet, Sprite hero) {
		super(new PhysicsGun(hero), new AnimationHeroArm(spriteSheet), new StateHeroArm(), new ControllerNone());
	}
	
	public void setAngle(float radians){
		((PhysicsGun) mPhysics).setAngleRadians(radians);
	}
	
	public void create(World world, Sprite hero){
		
		mPhysics.create(world, hero.getX(), hero.getY(), 0.5f, 0.5f, true, FixtureType.HERO_ARM);
		mPhysics.getBody().getFixtureList().get(0).getFilterData().groupIndex = PhysicsComponent.GROUP_ALLY;
		GameManager.getGameManager().getLevel().addDrawableSprite(this, Level.LAYER_UNDER_GFX);
		GameManager.getGameManager().getLevel().addUpdateableSprite(this);
	}
	
}

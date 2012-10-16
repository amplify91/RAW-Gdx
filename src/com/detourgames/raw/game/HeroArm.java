package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.ControllerNone;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.Level;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class HeroArm extends Sprite{
	
	public static final float WIDTH = Hero.WIDTH/2f;
	public static final float HEIGHT = Hero.HEIGHT/2f;
	
	//do not free child sprites of other sprites. This arm, for example, should not be recycled.
	//It should always stay with its parent and if we free() it to a pool, we can't guarantee that.
	
	public HeroArm(SpriteSheet spriteSheet, Sprite hero) {
		super(new PhysicsGun(hero, new Vector2(Hero.WIDTH/3f+0.05f,-0.03f), new Vector2(-WIDTH/2f,0f)), new AnimationHeroArm(spriteSheet), new StateHeroArm(), new ControllerNone());
	}
	
	public void rotateTo(float radians){
		((PhysicsGun) mPhysics).setAngleRadians(radians);
		((PhysicsGun) mPhysics).setRotatedPosition(radians);
	}
	
	public void create(){
		GameManager.getGameManager().getLevel().addDrawableSprite(this, Level.LAYER_UNDER_GFX);
		GameManager.getGameManager().getLevel().addUpdateableSprite(this);
	}
	
}

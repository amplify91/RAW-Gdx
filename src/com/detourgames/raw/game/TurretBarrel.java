package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.ControllerNone;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.Level;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class TurretBarrel extends Sprite{
	
	public TurretBarrel(SpriteSheet spriteSheet, Sprite turret) {
		super(new PhysicsGun(turret, new Vector2(Hero.WIDTH/3f+0.05f,-0.03f), new Vector2(-Hero.WIDTH/4f,0f)), new AnimationHeroArm(spriteSheet), new StateHeroArm(), new ControllerNone());
	}
	
	public void rotateTo(float radians){
		((PhysicsGun) mPhysics).setAngleRadians(radians);
		((PhysicsGun) mPhysics).setRotatedPosition(radians);
	}
	
	public void create(World world){
		GameManager.getGameManager().getLevel().addDrawableSprite(this, Level.LAYER_OVER_GFX);
		GameManager.getGameManager().getLevel().addUpdateableSprite(this);
	}
	
}

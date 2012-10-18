package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.ControllerNone;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.Level;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class TurretBarrel extends Sprite{
	
	public static final float WIDTH = Turret.WIDTH/2f;
	public static final float HEIGHT = Turret.HEIGHT/2f;
	
	public TurretBarrel(SpriteSheet spriteSheet, Sprite turret) {
		super(new PhysicsGun(turret, new Vector2(Turret.WIDTH/3f+0.05f,Turret.HEIGHT/3f), new Vector2(-WIDTH/0.6f,0f)), new AnimationTurretBarrel(spriteSheet), new StateHeroArm(), new ControllerNone(), null);
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

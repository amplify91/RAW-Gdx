package com.detourgames.raw.turretElement;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class TurretSprite extends Sprite{
	
	private static final float HEIGHT = 1;
	private static final float WIDTH = 1;
	
	public TurretSprite(SpriteSheet spriteSheet){
		super(new TurretComponent(), new AnimationTurret(spriteSheet,WIDTH,HEIGHT), new StateTurret(), new TurretController());
		
	}
	
	public void create(World world, float x, float y){
		mPhysics.create(world, x, y, WIDTH, HEIGHT, false, FixtureType.TURRET_BODY);
	}
	
}

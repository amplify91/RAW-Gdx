package com.detourgames.raw.game;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.game.AnimationTurret;
import com.detourgames.raw.game.StateTurret;

public class Turret extends Sprite{
	
	private static final float HEIGHT = 1;
	private static final float WIDTH = 1;
	
	public Turret(SpriteSheet spriteSheet){
		super(new PhysicsTurret(), new AnimationTurret(spriteSheet,WIDTH,HEIGHT), new StateTurret(), new ControllerTurret());
		
	}
	
	public void create(World world, float x, float y){
		mPhysics.create(world, x, y, WIDTH, HEIGHT, false, FixtureType.TURRET_BODY);
	}
	
}

package com.detourgames.raw.game;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.GenericPool;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteFactory;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.game.AnimationTurret;
import com.detourgames.raw.game.StateTurret;

public class Turret extends Sprite{
	
	TurretBarrel mBarrel;
	
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	
	public Turret(SpriteSheet spriteSheet, GenericPool<Turret> pool){
		super(new PhysicsTurret(), new AnimationTurret(spriteSheet,WIDTH,HEIGHT), new StateTurret(), new ControllerTurret());
		mPool = pool;
		mBarrel = new TurretBarrel(spriteSheet, this);
	}
	
	public void create(World world, float x, float y){
		mPhysics.create(world, x, y, WIDTH, HEIGHT, false, FixtureType.TURRET_BODY);
		mBarrel.create(world);
	}
	
}

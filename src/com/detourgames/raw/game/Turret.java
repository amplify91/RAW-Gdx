package com.detourgames.raw.game;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.EventQueue;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.GenericPool;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.game.launchers.IProjectileLauncher;

public class Turret extends Sprite{
	
	protected TurretBarrel mBarrel;
	protected IProjectileLauncher mLauncher;
	
	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;
	
	public Turret(SpriteSheet spriteSheet, GenericPool<Turret> pool){
		super(new PhysicsTurret(), new AnimationTurret(spriteSheet,WIDTH,HEIGHT), new StateTurret(), null, pool);
		this.mController=new ControllerTurret(this);
		mBarrel = new TurretBarrel(spriteSheet, this);
		mState.setInitialState();
	}
	
	public void SetLauncher(IProjectileLauncher launcher)
	{
		mLauncher=launcher;
	}
	public void SetController(ControllerComponent controller)
	{
		mController=controller;
	}
	
	public void create(World world, float x, float y){
		//mPhysics.create(world, x, y, WIDTH, HEIGHT, false, FixtureType.TURRET_BODY);
		mPhysics.createBody(world, x, y, false, true, false, 1, 0, 0);
		mPhysics.createFixture(WIDTH, WIDTH, FixtureType.TURRET_BODY, 1, 0, 0, false, PhysicsComponent.GROUP_ENEMY, PhysicsComponent.CATEGORY_ENEMY, PhysicsComponent.CATEGORY_ALLY);
		mBarrel.create(GameManager.getGameManager().getLevel().getWorld());
		addChildSprite(mBarrel);
	}

	public void fireMissileAtTarget(Sprite target){//todo Q: Move this to Turret class?
		EventQueue.getEventQueue().queue(new FireProjectileEvent(mLauncher, this, target, null));
		float radians = (float)Math.atan2(target.getX()-getY(), target.getY()-getX());
		mBarrel.rotateTo(radians);
	}
	
}

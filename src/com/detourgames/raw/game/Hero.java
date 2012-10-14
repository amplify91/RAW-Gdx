package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.GenericPool;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class Hero extends Sprite{
	
	Fixture mSensorFixture;
	HeroArm mArm;
	
	Vector2 running;
	//private static final float MAX_SPEED = 10;
	public static final float HEIGHT = 0.9f;
	public static final float WIDTH = 0.9f;
	private static final Vector2[] BODY_VERTS = {new Vector2(-0.45f,-0.45f),new Vector2(0.4f,-0.45f),new Vector2(0.45f,-0.4f),new Vector2(0.45f,0.45f),new Vector2(-0.45f,0.45f)};
	
	public Hero(SpriteSheet spriteSheet, GenericPool<Hero> pool){
		super(new PhysicsHero(), new AnimationHero(spriteSheet,WIDTH,HEIGHT), new StateHero(), new ControllerComponent());
		running = new Vector2(25, 0);
		mCameraOffsetX = (-1f * 12.5f)/6f;//TODO this is not how this should be done.
		mCameraOffsetY = (-1f * 7.5f)/6f; // Consider making changes to Camera class.
		//mAnimation.setAnimation(Animation.ANIMATION_RUNNING);
		mArm = new HeroArm(spriteSheet, this);
		mPool = pool;
		
	}
	
	public void create(World world, float x, float y){
		//mPhysics.create(world, x, y, WIDTH, HEIGHT, true, FixtureType.HERO_BODY);
		mPhysics.create(world, x, y, BODY_VERTS, true, FixtureType.HERO_BODY);
		
		//create ground sensor
		float sensorHeight = 0.1f;
		PolygonShape sensorBox = new PolygonShape();
		sensorBox.setAsBox((WIDTH-0.1f) / 2f, sensorHeight / 2f, new Vector2(0,(-HEIGHT/2f)-(sensorHeight/2f)), 0);
		FixtureDef sensorFixtureDef = new FixtureDef();
		sensorFixtureDef.shape = sensorBox;
		sensorFixtureDef.density = 1.0f;
		sensorFixtureDef.friction = 0.0f;
		sensorFixtureDef.isSensor = true;
		mSensorFixture = mPhysics.getBody().createFixture(sensorFixtureDef);
		Filter filter = mSensorFixture.getFilterData();
		filter.groupIndex = PhysicsComponent.GROUP_ALLY;
		mSensorFixture.setFilterData(filter);
		mSensorFixture.setUserData(FixtureType.HERO_GROUND_SENSOR);
		
		//create arm
		mArm.create(world, this);
	}
	
	public void jump(){
		if(((StateHero)mState).canJump){
			((PhysicsHero) mPhysics).jump();
			mState.setState(StateHero.STATE_JUMPING);
			((StateHero)mState).canJump = false;
		}else if(((StateHero)mState).canDoubleJump){
			((PhysicsHero) mPhysics).jump();
			mState.setState(StateHero.STATE_DOUBLE_JUMPING);
			((StateHero)mState).canDoubleJump = false;
		}
		
	}
	
	public void shoot(Vector2 target){
		((PhysicsHero) mPhysics).shoot(target);
		float angle = (float)Math.atan2(target.y-mPhysics.getY(), target.x-mPhysics.getX());
		mArm.setAngle(angle);
	}
	
	public void dash(){
		((PhysicsHero) mPhysics).dash();
		//mState.setState(StateHero.STATE_DASHING);
	}
	
	public void swing(){
		((PhysicsHero) mPhysics).swing();
	}
	
}

package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Hero extends Sprite{
	
	Vector2 running;
	private static final float MAX_SPEED = 10;
	private static final float HEIGHT = 0.9f;
	private static final float WIDTH = 0.9f;
	
	public Hero(SpriteSheet spriteSheet){
		super(new PhysicsHero(), new AnimationHero(spriteSheet,WIDTH,HEIGHT), new StateHero(), new ControllerComponent());
		running = new Vector2(25, 0);
		//mAnimation.setAnimation(Animation.ANIMATION_RUNNING);
		
	}
	
	public void create(World world, float x, float y){
		mPhysics.create(world, x, y, WIDTH, HEIGHT, true, FixtureType.HERO_BODY);
		
		//create ground sensor
		float sensorHeight = 0.1f;
		PolygonShape sensorBox = new PolygonShape();
		sensorBox.setAsBox((WIDTH-0.1f) / 2f, sensorHeight / 2f, new Vector2(0,(-HEIGHT/2f)-(sensorHeight/2f)), 0);
		FixtureDef sensorFixtureDef = new FixtureDef();
		sensorFixtureDef.shape = sensorBox;
		sensorFixtureDef.density = 1.0f;
		sensorFixtureDef.friction = 0.0f;
		sensorFixtureDef.isSensor = true;
		mPhysics.mBody.createFixture(sensorFixtureDef).setUserData(FixtureType.HERO_GROUND_SENSOR);
	}
	
	public void jump(){
		((PhysicsHero) mPhysics).jump();
		mState.setState(StateHero.STATE_JUMPING);
	}
	
	public void dash(){
		((PhysicsHero) mPhysics).dash();
		//mState.setState(StateHero.STATE_DASHING);
	}
	
}

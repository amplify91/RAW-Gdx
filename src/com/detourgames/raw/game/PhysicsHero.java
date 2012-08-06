package com.detourgames.raw.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.detourgames.raw.PhysicsComponent;


public class PhysicsHero extends PhysicsComponent{
	
	private Vector2 mVel = new Vector2(); // Current velocity.
	private Vector2 mRunVel = new Vector2(); // Impulse velocity that needs to be applied to run at correct speed
	private Vector2 mJumpVel = new Vector2(0, 10);//TODO make movement velocities functions of mass and time
	private Vector2 mDashVel = new Vector2(20, 0);
	private Vector2 mTotalVel = new Vector2();
	
	private float velChange;
	private float runImpulse;
	
	private static final float MAX_SPEED = 2f;
	
	public PhysicsHero(){
		super();
	}
	
	@Override
	public void update() {
		mBody.setLinearVelocity(mBody.getLinearVelocity().mul(0.9f)); // Slow down, in case we're above max speed.
		run();
		//getInput();
		mBody.applyLinearImpulse(mTotalVel, mBody.getWorldCenter());
		mTotalVel.set(Vector2.Zero);
	}
	
	private void run(){
		mRunVel.set(Vector2.Zero);
		mVel = mBody.getLinearVelocity();
		velChange = MAX_SPEED - mVel.x;
	    runImpulse = mBody.getMass() * velChange;
	    if(runImpulse>0){
	    	mRunVel.set(runImpulse, 0);
	    }
	    mTotalVel = mTotalVel.add(mRunVel);
	}
	
	public void jump(){
		mTotalVel = mTotalVel.add(mJumpVel);
	}
	
	public void dash(){
		mTotalVel = mTotalVel.add(mDashVel);
	}
	
	@Override
	public void create(World world, float x, float y, float width, float height, boolean dynamic, int mainFixtureType) {

		BodyDef bodyDef = new BodyDef();
		if (dynamic) {
			bodyDef.type = BodyType.DynamicBody;
		} else {
			bodyDef.type = BodyType.StaticBody;
		}
		bodyDef.position.set(x, y);// (x+(width/2f), y+(height/2f))
		mBody = world.createBody(bodyDef);

		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(width / 2f, height / 2f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.0f;
		fixtureDef.filter.groupIndex = PhysicsComponent.GROUP_ALLY;
		mBody.createFixture(fixtureDef).setUserData(mainFixtureType);
		mBody.setGravityScale(1.4f);
		mBody.setFixedRotation(true);
		mBody.setUserData(this);
	}
}

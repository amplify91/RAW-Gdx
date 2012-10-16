package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PhysicsComponent {
	
	protected Sprite mParent;
	protected Body mBody = null;
	Vector2[] mVertices;
	protected float mAngle = 0;//radians
	protected float mXPos = 0;
	protected float mYPos = 0;
	
	//collision group indices
	//negative means they never collide with each other, positive means always collide
	//if groups don't match or either one is 0, determine collision by category/mask
	public static final short GROUP_NO_COLLISIONS = 0;
	public static final short GROUP_ALLY = -1;
	public static final short GROUP_ENEMY = -2;
	public static final short GROUP_NEUTRAL = -3;
	public static final short GROUP_TERRAIN = -4;
	public static final short GROUP_GFX = -5; //graphic effects
	public static final short GROUP_SPECIAL = -6; //power-ups and items, etc.
	
	//collision categories
	//category bit means "I am a...", mask bit means "I collide with..."
	public static final short CATEGORY_NO_COLLISIONS = 0;
	public static final short CATEGORY_ALLY = 1;
	public static final short CATEGORY_ENEMY = 2;

	public PhysicsComponent() {
		
	}

	public void create(World world, float x, float y, float width, float height, boolean dynamic, int mainFixtureType) {
		
		//create main fixture (bodies may have more than one)
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
		mBody.createFixture(fixtureDef).setUserData(mainFixtureType);
		//mBody.setFixedRotation(true);
		mBody.setUserData(this); //TODO If subclass overrides, make sure to call this.

	}

	public void create(World world, float x, float y, Vector2 vertices[], boolean dynamic, int mainFixtureType) {
		
		//create main fixture (bodies may have more than one)
		BodyDef bodyDef = new BodyDef();
		if (dynamic) {
			bodyDef.type = BodyType.DynamicBody;
		} else {
			bodyDef.type = BodyType.StaticBody;
		}
		bodyDef.position.set(x, y);
		mBody = world.createBody(bodyDef);

		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.set(vertices);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.0f;
		mBody.createFixture(fixtureDef).setUserData(mainFixtureType);
		//mBody.setFixedRotation(true);
		mBody.setUserData(this);

	}

	public abstract void update();
	
	public Sprite getParentSprite(){
		return mParent;
	}
	
	public void setParentSprite(Sprite parent){
		mParent = parent;
	}
	
	public void destroy() {
		// TODO
	}

	public float getX() {
		if(mBody!=null){
			return mBody.getPosition().x;
		}else{
			return mXPos;
		}
	}

	public float getY() {
		if(mBody!=null){
			return mBody.getPosition().y;
		}else{
			return mYPos;
		}
	}
	
	public float getAngle(){
		if(mBody!=null){
			return mBody.getAngle();
		}else{
			return mAngle;
		}
	}
	
	public void setAngleRadians(float radians){
		mAngle = radians;
	}
	
	public void setAngleDegrees(float degrees){
		mAngle = (float)Math.toRadians(degrees);
	}
	
	public Vector2 getPosition(){
		return mBody.getPosition();
	}
	public Body getBody(){
		return mBody;
	}
	
	public Vector2 getDistanceVectorToPoint(Vector2 point){
		return new Vector2(point.x-getX(), point.y-getY());
	}

}

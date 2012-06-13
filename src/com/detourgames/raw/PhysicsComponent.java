package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PhysicsComponent {

	Body mBody;
	Vector2[] mVertices;
	
	//collision filters
	static final short TYPE_UNBODIED=0;
	static final short TYPE_TERRAIN = 1;
	static final short TYPE_RAW = 2;
	static final short TYPE_ENEMY = 3;
	

	public PhysicsComponent() {
		
	}

	public void create(World world, float x, float y, float width, float height, boolean dynamic) {

		BodyDef bodyDef = new BodyDef();
		if (dynamic) {
			bodyDef.type = BodyType.DynamicBody;
		} else {
			bodyDef.type = BodyType.StaticBody;
		}
		bodyDef.position.set(x, y);// (x+(width/2f), y+(height/2f))
		mBody = world.createBody(bodyDef);

		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(width / 2, height / 2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.0f;
		mBody.createFixture(fixtureDef);
		mBody.setFixedRotation(true);

	}

	public void create(World world, float x, float y, Vector2 vertices[], boolean dynamic) {

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
		mBody.createFixture(fixtureDef);
		mBody.setFixedRotation(true);

	}

	public abstract void update();

	public void destroy() {
		// TODO
	}

	public float getX() {
		return mBody.getPosition().x;
	}

	public float getY() {
		return mBody.getPosition().y;
	}
	
	public Body getBody(){return mBody;};
	public Vector2 getDistanceVectorToPoint(Vector2 point)
	{
		return new Vector2(point.x-this.getX(),point.y-this.getY());
	}

}

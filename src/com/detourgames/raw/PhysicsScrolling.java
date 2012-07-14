package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PhysicsScrolling extends PhysicsComponent{
	
	//Physics for sprites like parallax backgrounds that
	
	private IFocusable mFocus;
	private Vector2 mScrollDirection = new Vector2(1,0);
	private float mScrollSpeed = 1.0f;// likely between 0 and 1
	
	private float mFocusLastX;
	private float mFocusLastY;
	private float mFocusX;
	private float mFocusY;
	private float mFocusMoveX;
	private float mFocusMoveY;
	
	public PhysicsScrolling(IFocusable focus){
		if(focus==null){
			System.out.println("Null focus for PhysicsScrolling!");
		}
		mFocus = focus;
		mFocusLastX = mFocus.getX();
		mFocusLastY = mFocus.getY();
	}
	
	public PhysicsScrolling(IFocusable focus, float scrollSpeed){
		if(focus==null){
			System.out.println("Null focus for PhysicsScrolling!");
		}
		mFocus = focus;
		mScrollSpeed = scrollSpeed;
		mFocusLastX = mFocus.getX();
		mFocusLastY = mFocus.getY();
	}
	
	@Override
	public void update() {
		mFocusX = mFocus.getX();
		mFocusY = mFocus.getY();
		mFocusMoveX = mFocusX - mFocusLastX;
		mFocusMoveY = mFocusY - mFocusLastY;
		
		mBody.setTransform(mBody.getPosition().x + (mFocusMoveX * mScrollDirection.x * mScrollSpeed),
				mBody.getPosition().y + (mFocusMoveY * mScrollDirection.y * mScrollSpeed), mBody.getAngle());
		
		mFocusLastX = mFocusX;
		mFocusLastY = mFocusY;
	}
	
	public void create(World world, float x, float y, float width, float height){
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		mBody = world.createBody(bodyDef);
		
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(width / 2f, height / 2f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.0f;
		fixtureDef.filter.groupIndex = PhysicsComponent.GROUP_NO_COLLISIONS;
		fixtureDef.filter.maskBits = PhysicsComponent.CATEGORY_NO_COLLISIONS;
		mBody.createFixture(fixtureDef);
		mBody.setFixedRotation(true);
		mBody.setGravityScale(0);
		mBody.setUserData(this);
		
	}
	
	public void setFocus(IFocusable focus){
		if(focus==null){
			System.out.println("Null focus for PhysicsScrolling!");
		}
		mFocus = focus;
	}
	public void setScrollDirection(Vector2 normalizedDirection){
		mScrollDirection = normalizedDirection;
	}
	
	public void setScrollSpeed(float scrollSpeed){
		mScrollSpeed = scrollSpeed;
	}
	
}

package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Sprite implements IFocusable{

	PhysicsComponent mPhysics;
	StateComponent mState;
	AnimationComponent mAnimation;
	ControllerComponent mController = new ControllerComponent();

	float mDrawWidth = 0.5f;
	float mDrawHeight = 0.5f;
	float mDrawOffsetX = 0;
	float mDrawOffsetY = 0;
	float mCameraOffsetX = 0;
	float mCameraOffsetY = 0;

	// private Vector2 mProjectileSpawnPoint = new Vector2();

	public static final int VERTEX_SIZE = 2 + 2;
	public static final int SPRITE_SIZE = 4 * VERTEX_SIZE;
	// TODO put these 2 fields into render components
	public static final float SCALE_FACTOR = 2f / 15f;
	public static final float SCALE_FACTOR_INV = 15f / 2f;

	public Sprite(PhysicsComponent pc, AnimationComponent ac, StateComponent sc) {
		
		// all sprites will create their own animations in their own constructors.
		// that means each sprite subclass must have a SpriteSheet param in their constructor.
		mPhysics = pc;
		mAnimation = ac;
		mState = sc;
		mPhysics.setParentSprite(this);//TODO see if this works or else I need to do this in each subclass

	}

	public void destroy() {
		// TODO
	}

	public void draw(SpriteBatch sb, long nanoTime) {
		sb.draw(mAnimation.getFrame(nanoTime), mPhysics.getX()+mDrawOffsetX, mPhysics.getY()+mDrawOffsetY, mDrawWidth, mDrawHeight);
		//sb.draw(mAnimation.getFrame(nanoTime), mPhysics.getX()+mDrawOffsetX, mPhysics.getY()+mDrawOffsetY, 0, 0, mDrawWidth, mDrawHeight, 1, 1, mPhysics.mBody.getAngle()+((float)Math.PI/2f), false);
	}

	public void update(float deltaTime) {
		
		mState.update(mPhysics);
		mAnimation.update(mState);
		mController.update(mState, mPhysics);
		mPhysics.update();
		
	}

	public void getInput() {

	}

	public float getX() {
		return mPhysics.getX();
	}

	public float getY() {
		return mPhysics.getY();
	}
	
	@Override
	public float getCameraOffsetX() {
		return mCameraOffsetX;
	}

	@Override
	public float getCameraOffsetY() {
		return mCameraOffsetY;
	}

	/*
	 * public float getOriginX(){ return mPhysics.getX() + mDrawWidth/2f; }
	 * 
	 * public float getOriginY(){ return mPhysics.getY() + mDrawHeight/2f; }
	 * 
	 * public Vec2 getProjectileSpawnPoint(){//TODO move to an interface like
	 * IProjectileParent or something
	 * mProjectileSpawnPoint.set(mPhysics.getX()+3, mPhysics.getY());//TODO
	 * change arguments to mPhysics.getProjectileSpawn return
	 * mProjectileSpawnPoint; }
	 */
	
}
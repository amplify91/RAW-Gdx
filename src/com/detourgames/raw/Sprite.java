package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.detourgames.raw.game.SpriteDeathEvent;

public abstract class Sprite implements IFocusable{

	protected PhysicsComponent mPhysics;
	protected StateComponent mState;
	protected AnimationComponent mAnimation;
	protected ControllerComponent mController;
	protected GenericPool<?> mPool;
	
	protected float mCameraOffsetX = 0;
	protected float mCameraOffsetY = 0;

	public Sprite(PhysicsComponent pc, AnimationComponent ac, StateComponent sc, ControllerComponent cc) {
		
		// all sprites will create their own animations in their own constructors.
		// that means each sprite subclass must have a SpriteSheet param in their constructor.
		mPhysics = pc;
		mAnimation = ac;
		mState = sc;
		mController = cc;
		mPhysics.setParentSprite(this);

	}
	
	public void recycle(){
		mPool.free(this);
		mPhysics.recycle();
	}
	
	//public abstract void create(World world, float x, float y);

	public void draw(SpriteBatch sb, long nanoTime) {
		//sb.draw(mAnimation.getFrame(nanoTime), mPhysics.getX()+mAnimation.getOffsetX(), mPhysics.getY()+mAnimation.getOffsetY(), mAnimation.getWidth(), mAnimation.getHeight());
		sb.draw(mAnimation.getFrame(nanoTime), mPhysics.getX()+mAnimation.getOffsetX(), mPhysics.getY()+mAnimation.getOffsetY(), mAnimation.getWidth()/2f, mAnimation.getHeight()/2f, mAnimation.getWidth(), mAnimation.getHeight(), 1, 1, (float)Math.toDegrees(mPhysics.getAngle()));
	}

	public void update(float deltaTime) {
		
		mState.update(mPhysics);
		if(mState.getState()!=StateComponent.STATE_DEAD){
			mAnimation.update(mState);
			mController.update(mState, mPhysics);
			mPhysics.update();
		}
		
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

	public Vector2 getPosition() {
		return mPhysics.getPosition();
	}
	
	public Body getBody(){
		return mPhysics.getBody();
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
	
	public void die(){
		System.out.println("Kill: "+this);
		Vector2 deathPosition = mPhysics.getPosition();
		float deathAngle = mPhysics.getAngle();
		EventQueue.getEventQueue().queue(new SpriteDeathEvent(this));
		//TODO remove child sprites
		//suggest adding abstract createChildren() and destroyChildren()
		//call destroyChildren() here.
	}
}	

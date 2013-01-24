package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.detourgames.raw.game.SpawnGFXEvent;
import com.detourgames.raw.game.SpriteDeathEvent;

public abstract class Sprite implements IFocusable, Recyclable{

	protected PhysicsComponent mPhysics;
	protected StateComponent mState;
	protected AnimationComponent mAnimation;
	protected ControllerComponent mController;
	protected GenericPool<?> mPool = null;
	
	protected Array<Sprite> mChildSprites = null;
	
	protected float mCameraOffsetX = 0;
	protected float mCameraOffsetY = 0;

	public Sprite(PhysicsComponent pc, AnimationComponent ac, StateComponent sc, ControllerComponent cc, GenericPool<?> pool) {
		
		// all sprites will create their own animations in their own constructors.
		// that means each sprite subclass must have a SpriteSheet param in their constructor.
		mPhysics = pc;
		mAnimation = ac;
		mState = sc;
		mController = cc;
		mPool = pool;
		mPhysics.setParentSprite(this);

	}
	
	public void destroy(){
		//System.out.println("Sprite = "+this+" Pool = "+mPool );
		destroyChildSprites();
		if(getBody()!=null){
			SpriteFactory.getSpriteFactory().getLevel().getWorld().destroyBody(getBody());
		}
		if(mPool!=null){
			mPool.free(this);
		}
		mPhysics.recycle();
	}
	
	protected void addChildSprite(Sprite sprite){
		if(mChildSprites==null){
			mChildSprites = new Array<Sprite>(false, 1);
		}
		mChildSprites.add(sprite);
	}
	
	protected void destroyChildSprites(){
		if(mChildSprites!=null){
			for(Sprite sprite : mChildSprites){
				sprite.destroy();
			}
		}
	}

	public void draw(SpriteBatch sb, long nanoTime) {
		//sb.draw(mAnimation.getFrame(nanoTime), mPhysics.getX()+mAnimation.getOffsetX(), mPhysics.getY()+mAnimation.getOffsetY(), mAnimation.getWidth(), mAnimation.getHeight());
		sb.draw(mAnimation.getFrame(nanoTime), mPhysics.getX()+mAnimation.getOffsetX(), mPhysics.getY()+mAnimation.getOffsetY(), mAnimation.getWidth()/2f, mAnimation.getHeight()/2f, mAnimation.getWidth(), mAnimation.getHeight(), 1, 1, (float)Math.toDegrees(mPhysics.getAngle()));
	}

	public void update(long nanoTime, float deltaTime) {
		
		mState.update(mPhysics, nanoTime);
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
	public PhysicsComponent getPhysics(){
		return mPhysics;
	}
	
	public AnimationComponent getAnimationComponent(){
		return mAnimation;
	}
	//TODO make getters for other components

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
		//System.out.println("Kill: "+this);
		spawnDeathGFX();
		EventQueue.getEventQueue().queue(new SpriteDeathEvent(this));
		//TODO remove child sprites
		//suggest adding abstract createChildren() and destroyChildren()
		//call destroyChildren() here.
	}
	
	private void spawnDeathGFX(){
		if(mAnimation.getDeathAnimation()==null){
			System.out.println("Trying to kill something without a death animation. Sprite: "+this);
			return;
		}else if(mAnimation.getDeathAnimation().length!=2){
			System.out.println("Improper death animation. Sprite: "+this);
		}
		EventQueue.getEventQueue().queue(new SpawnGFXEvent(mPhysics.getPosition(), mAnimation.getWidth(), mAnimation.getHeight(), mPhysics.getAngle(), mAnimation.getDeathAnimation()[0], mAnimation.getDeathAnimation()[1]));
	}
}	

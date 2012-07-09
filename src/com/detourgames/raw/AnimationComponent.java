package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

public abstract class AnimationComponent {
	
	private Animation[] mAnimations;
	private Animation mAnimation;
	protected int mCurrentAnimation = -1;
	private int mNextAnimation = -1;
	
	private float mStateTime = 0f;
	private long mStartTime = 0;
	private boolean isAnimationFinished = false;
	private boolean isAnimationQueued = false;
	
	public static final float FPS = 12;
	public static final float FRAME_DURATION = 1f / (float) FPS;
	
	private float mWidth = 0.5f;
	private float mHeight = 0.5f;
	private float mOffsetX = 0;
	private float mOffsetY = 0;
	
	public AnimationComponent() {
		//If using this constructor, you must set animations before you try to draw.
		//Otherwise, getFrame() will throw null pointer or some other bug will happen.
	}
	
	public AnimationComponent(Animation[] animations) {
		setAnimations(animations);
		setAnimation(0);
	}
	
	public AnimationComponent(Animation[] animations, float width, float height) {
		setAnimations(animations);
		setAnimation(0);
		setWidth(width);
		setHeight(height);
		calculateOffset();
	}

	public abstract void update(StateComponent state);
	
	public TextureRegion getFrame(long nanoTime){//TODO give current time as a parameter and calculate state time from there
		
		mStateTime = (float)(nanoTime-mStartTime)/1000000000f;
		if(!isAnimationFinished){
			isAnimationFinished = mAnimation.isAnimationFinished(mStateTime);
			//TODO at this point, if an animation is finished, it will loop back and display first frame before being able to be changed.
			// Suggest moving this small block to an update() method or some other solution.
		}
		if(isAnimationFinished){
			if(isAnimationQueued){
				setQueuedAnimation();
			}
		}
		return mAnimation.getKeyFrame(mStateTime, true);
	}
	
	public void setAnimation(Animation animation){
		mAnimation = animation;
		isAnimationFinished = false;
		mStartTime = TimeUtils.nanoTime();
	}
	
	public void setAnimation(int animation){
		mCurrentAnimation = animation;
		mAnimation = mAnimations[mCurrentAnimation];
		isAnimationFinished = false;
		mStartTime = TimeUtils.nanoTime();
	}
	
	public void setTransitionAnimation(int animation, int nextAnimation){
		setAnimation(animation);
		queueAnimation(nextAnimation);
	}
	
	private void setQueuedAnimation(){
		setAnimation(mNextAnimation);
		isAnimationQueued = false;
		mStateTime = (float)1f/1000000000f;
	}
	
	private void queueAnimation(int nextAnimation){
		//TODO
		mNextAnimation = nextAnimation;
		isAnimationQueued = true;
	}
	
	public void setAnimations(Animation[] animations){
		mAnimations = animations;
		setAnimation(0);
	}
	
	public void restartAnimation(){
		isAnimationFinished = false;
		mStartTime = TimeUtils.nanoTime();
	}
	
	public boolean isAnimationFinished(){
		return isAnimationFinished;
	}
	
	public static Animation createAnimation(SpriteSheet spriteSheet, int[] frameNumbers){
		TextureRegion[] frames = new TextureRegion[frameNumbers.length];
		for(int i=0;i<frames.length;i++){
			frames[i] = new TextureRegion(spriteSheet.getTexture(), spriteSheet.getFrames()[frameNumbers[i]][0], spriteSheet.getFrames()[frameNumbers[i]][1], spriteSheet.getFrames()[frameNumbers[i]][2], spriteSheet.getFrames()[frameNumbers[i]][3]);
		}
		return new Animation(FRAME_DURATION, frames);
	}
	
	public float getWidth(){
		return mWidth;
	}
	
	public void setWidth(float width){
		mWidth = width;
	}
	
	public float getHeight(){
		return mHeight;
	}
	
	public void setHeight(float height){
		mHeight = height;
	}
	
	public void setSize(float width, float height){
		setWidth(width);
		setHeight(height);
		calculateOffset();
	}
	
	public float getOffsetX(){
		return mOffsetX;
	}
	
	public float getOffsetY(){
		return mOffsetY;
	}
	
	private void calculateOffset(){
		//update mOffsetX and Y according to height and width
		mOffsetX = -(mWidth/2f);
		mOffsetY = -(mHeight/2f);
	}
	
}

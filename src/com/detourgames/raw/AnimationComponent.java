package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

public abstract class AnimationComponent {
	
	private Animation[] mAnimations;
	private Animation mAnimation;
	protected int mCurrentAnimation = 0;
	
	private float mStateTime = 0f;
	private long mStartTime = 0;
	
	public static final float FPS = 12;
	public static final float FRAME_DURATION = 1f / (float) FPS;
	
	public AnimationComponent() {
		//If using this constructor, you must set animations before you try to draw.
		//Otherwise, getFrame() will throw null pointer or some other bug will happen.
	}
	
	public AnimationComponent(Animation[] animations) {
		setAnimations(animations);
		setAnimation(0);
	}

	public abstract void update(StateComponent state);
	
	public TextureRegion getFrame(long nanoTime){//TODO give current time as a parameter and calculate state time from there
		
		mStateTime = (float)(nanoTime-mStartTime)/1000000000f;
		return mAnimation.getKeyFrame(mStateTime, true);
	}
	
	public void setAnimation(Animation animation){
		mAnimation = animation;
		mStartTime = TimeUtils.nanoTime();
	}
	
	public void setAnimation(int animation){
		mCurrentAnimation = animation;
		mAnimation = mAnimations[mCurrentAnimation];
		mStartTime = TimeUtils.nanoTime();
	}
	
	public void setAnimations(Animation[] animations){
		mAnimations = animations;
		setAnimation(0);
	}
	
	public void restartAnimation(){
		mStartTime = TimeUtils.nanoTime();
	}
	
	public static Animation createAnimation(SpriteSheet spriteSheet, int[] frameNumbers){
		TextureRegion[] frames = new TextureRegion[frameNumbers.length];
		for(int i=0;i<frames.length;i++){
			frames[i] = new TextureRegion(spriteSheet.getTexture(), spriteSheet.getFrames()[frameNumbers[i]][0], spriteSheet.getFrames()[frameNumbers[i]][1], spriteSheet.getFrames()[frameNumbers[i]][2], spriteSheet.getFrames()[frameNumbers[i]][3]);
		}
		return new Animation(FRAME_DURATION, frames);
	}
	
}

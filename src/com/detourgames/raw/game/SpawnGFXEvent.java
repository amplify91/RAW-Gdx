package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Event;
import com.detourgames.raw.SpriteFactory;

public class SpawnGFXEvent extends Event{
	
	float mX;
	float mY;
	float mWidth;
	float mHeight;
	float mAngle;
	Vector2 mPosition;
	Animation mAnimation;
	Animation mFinalFrame;
	
	public SpawnGFXEvent(float x, float y, float width, float height, float angle, Animation animation, Animation finalFrame){
		mX = x;
		mY = y;
		mPosition = new Vector2(x, y);
		mWidth = width;
		mHeight = height;
		mAngle = angle;
		mAnimation = animation;
		mFinalFrame = finalFrame;
	}
	
	public SpawnGFXEvent(Vector2 position, float width, float height, float angle, Animation animation, Animation finalFrame){
			mPosition = position;
			mX = position.x;
			mY = position.y;
			mWidth = width;
			mHeight = height;
			mAngle = angle;
			mAnimation = animation;
			mFinalFrame = finalFrame;
	}
	
	@Override
	public void executeEvent() {
		SpriteFactory.getSpriteFactory().createGFX(mWidth, mHeight, mAngle, mPosition, mAnimation, mFinalFrame);
		
	}

}

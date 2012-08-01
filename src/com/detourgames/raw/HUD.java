package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {
	
	private float mScreenWidth;
	private float mScreenHeight;
	
	private float mJumpXMin;
	private float mJumpXMax;
	private float mJumpYMin;
	private float mJumpYMax;
	private static final float JUMP_ORIGIN_X = 0; // 0-1
	private static final float JUMP_ORIGIN_Y = 0;
	private static final float JUMP_WIDTH = 0.1f;
	private static final float JUMP_HEIGHT = 1f/6f;
	
	public static final int BUTTON_NONE = 0;
	public static final int BUTTON_JUMP = 1;
	public static final int BUTTON_DASH = 2;
	
	public HUD(Camera camera){
		setup(camera);
	}
	
	public void draw(SpriteBatch spriteBatch){
		
	}
	
	public boolean checkButton(int x, int y, int button){
		
		int y2 = (int) (y - (mScreenHeight-1));
		y2 *= -1;
		if(x>=mJumpXMin&&x<=mJumpXMax&&y2>=mJumpYMin&&y2<=mJumpYMax){
			button = BUTTON_JUMP;
			return true;
		}else{
			button = BUTTON_NONE;
			return false;
		}
		
	}
	
	public void setup(Camera camera){
		mScreenWidth = camera.getWidthPixels();
		mScreenHeight = camera.getHeightPixels();
		sizeButtons();
	}
	
	private void sizeButtons(){
		mJumpXMin = JUMP_ORIGIN_X * mScreenWidth;
		mJumpXMax = mJumpXMin + (JUMP_WIDTH * mScreenWidth);
		mJumpYMin = JUMP_ORIGIN_Y * mScreenHeight;
		mJumpYMax = mJumpYMin + (JUMP_HEIGHT * mScreenHeight);
	}
	
}

package com.detourgames.raw;

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
	
	public HUD(Camera camera){
		mScreenWidth = camera.getWidthPixels();
		mScreenHeight = camera.getHeightPixels();
		sizeButtons();
	}
	
	public void draw(){
		
	}
	
	public boolean checkButton(int x, int y){
		
		int y2 = (int) (y - (mScreenHeight-1));
		y2 *= -1;
		if(x>=mJumpXMin&&x<=mJumpXMax&&y2>=mJumpYMin&&y2<=mJumpYMax){
			return true;
		}
		return false;
	}
	
	private void sizeButtons(){
		mJumpXMin = JUMP_ORIGIN_X * mScreenWidth;
		mJumpXMax = mJumpXMin + (JUMP_WIDTH * mScreenWidth);
		mJumpYMin = JUMP_ORIGIN_Y * mScreenHeight;
		mJumpYMax = mJumpYMin + (JUMP_HEIGHT * mScreenHeight);
	}
	
}

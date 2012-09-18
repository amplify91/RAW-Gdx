package com.detourgames.raw.engineTest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Camera;
import com.detourgames.raw.HUDElement;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.game.HUDButtonDash;
import com.detourgames.raw.game.HUDButtonJump;

public class MenuHUD {
	
	private Camera mCamera;
	
	private float mScreenWidth;
	private float mScreenHeight;
	
	public static final int BUTTON_NONE = 0;
	public static final int BUTTON_JUMP = 1;
	public static final int BUTTON_DASH = 2;
	
	MenuHUDElement[] mHUDElements;
	
	public MenuHUD(Camera camera){
		mCamera = camera;
	}
	
	public void draw(SpriteBatch sb, long nanoTime){
		for(int i=0;i<mHUDElements.length;i++){
			mHUDElements[i].draw(sb, nanoTime);
		}
	}
	
	public int checkButton(int x, int y){
		
		//int y2 = (int) (y - (mCamera.getHeightPixels()-1));
		//y2 *= -1;
		int button = 0;
		Vector2 vec = mCamera.translatePixelToWorldCoordinates(x, y);
		for(int i=0;i<mHUDElements.length;i++){
			if(mHUDElements[i].isTouchInside(vec.x, vec.y)){
				button = i+1;//TODO make sure mHUDElements is constructed in the same order as the button constants.
				mHUDElements[i].touchDown();
				return button;
			}
		}
		button = BUTTON_NONE;
		return button;
	}
	
	public void createElements(MenuLevel level, SpriteSheet spriteSheet){
		mHUDElements = new MenuHUDElement[]{
				new MenuHUDButtonNewGame(mCamera,level.getWorld(),spriteSheet)
		};
		for(int i=0;i<mHUDElements.length;i++){
			level.addDrawableSprite(mHUDElements[i], MenuLevel.LAYER_HUD);
			level.addUpdateableSprite(mHUDElements[i]);
		}
	}
	
}
package com.detourgames.raw.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Camera;
import com.detourgames.raw.HUDElement;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.Testing.MenuHUDButtonTests;
import com.detourgames.raw.game.HUDButtonDash;
import com.detourgames.raw.game.HUDButtonJump;

public class MenuHUD {
	
	private Camera mCamera;
	
	private float mScreenWidth;
	private float mScreenHeight;
	
	
	MenuHUDElement[] mHUDElements;
	
	public MenuHUD(Camera camera){
		mCamera = camera;
	}
	
	public void draw(SpriteBatch sb, long nanoTime){
		for(int i=0;i<mHUDElements.length;i++){
			mHUDElements[i].draw(sb, nanoTime);
		}
	}
	
	public MenuHUDElement checkButton(int x, int y){
		
		Vector2 vec = mCamera.translatePixelToWorldCoordinates(x, y);
		for(int i=0;i<mHUDElements.length;i++){
			if(mHUDElements[i].isTouchInside(vec.x, vec.y)){
				mHUDElements[i].touchDown();
				return mHUDElements[i];
			}
		}
		return null;
	}
	
	public void createElements(MenuLevel level, SpriteSheet spriteSheet){
		mHUDElements = new MenuHUDElement[]{
				new MenuHUDButtonNewGame(mCamera,level.getWorld(),spriteSheet),
				new MenuHUDButtonTests(mCamera,level.getWorld(),spriteSheet)
		};
		for(int i=0;i<mHUDElements.length;i++){
			level.addDrawableSprite(mHUDElements[i], MenuLevel.LAYER_HUD);
			level.addUpdateableSprite(mHUDElements[i]);
		}
	}
	
}

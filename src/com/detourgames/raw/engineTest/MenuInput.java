package com.detourgames.raw.engineTest;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.EventQueue;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.HUD;

public class MenuInput implements InputProcessor{
	
	private int mButton = 0;
	
	public static final int BUTTON_NONE = 0;
	public static final int BUTTON_JUMP = 1;
	public static final int BUTTON_DASH = 2;
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode==Keys.UP || keycode==Keys.W){
			return true;
		}else if(keycode==Keys.RIGHT || keycode==Keys.D){
			return true;
		}
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return checkHUDButtons(x,y);
	}

	private boolean checkHUDButtons(int x, int y) {
		
		mButton = 0;
		mButton = MenuManager.getGameManager().getHUD().checkButton(x, y);
		
		if(mButton==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}


}

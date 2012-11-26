package com.detourgames.raw.Menu;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.EventQueue;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.HUD;

public class MenuInput implements InputProcessor{
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.UP || keycode==Keys.W){
			return true;
		}else if(keycode==Keys.RIGHT || keycode==Keys.D){
			return true;
		}
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return checkHUDButtons(x,y);
	}

	private boolean checkHUDButtons(int x, int y) {
		
		MenuHUDElement elementFound = MenuManager.getGameManager().getHUD().checkButton(x, y);
		
		if(elementFound!=null)
			return true;
	return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}


}

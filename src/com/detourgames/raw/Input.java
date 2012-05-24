package com.detourgames.raw;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor{

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode==Keys.UP){
			jump();
		}else if(keycode==Keys.RIGHT){
			dash();
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
		
		if(!checkHUDButtons(x, y)){
			shoot(x, y);
		}
		return false;
	}

	private boolean checkHUDButtons(int x, int y) {
		// TODO Auto-generated method stub
		return false;
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
	
	private void dash(){
		EventQueue.getEventQueue().queue(new DashEvent());
	}
	
	private void jump(){
		EventQueue.getEventQueue().queue(new JumpEvent());
	}
	
	private void shoot(int x, int y){
		EventQueue.getEventQueue().queue(new FireProjectileEvent(x, y));
	}
	
}

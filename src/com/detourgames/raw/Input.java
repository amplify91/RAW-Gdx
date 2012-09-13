package com.detourgames.raw;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.game.DashEvent;
import com.detourgames.raw.game.FireProjectileEvent;
import com.detourgames.raw.game.JumpEvent;
import com.detourgames.raw.game.Projectile;
import com.detourgames.raw.game.SwingEvent;

public class Input implements InputProcessor{
	
	private int mButton = 0;
	
	public static final int BUTTON_NONE = 0;
	public static final int BUTTON_JUMP = 1;
	public static final int BUTTON_DASH = 2;
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode==Keys.UP || keycode==Keys.W){
			jump();
			return true;
		}else if(keycode==Keys.RIGHT || keycode==Keys.D){
			dash();
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
		
		if(checkHUDButtons(x, y)){
			if(mButton==HUD.BUTTON_JUMP){
				jump();
			}else if(mButton==HUD.BUTTON_DASH){
				dash();
			}
			return true;
		}else{
			shoot(x, y);
			return true;
		}
	}

	private boolean checkHUDButtons(int x, int y) {
		
		mButton = 0;
		mButton = GameManager.getGameManager().getHUD().checkButton(x, y);
		
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
	
	private void dash(){
		EventQueue.getEventQueue().queue(new DashEvent());
	}
	
	private void jump(){
		EventQueue.getEventQueue().queue(new JumpEvent());
	}
	
	private void swing(){
		EventQueue.getEventQueue().queue(new SwingEvent());
	}
	
	private void shoot(int x, int y){
		Vector2 worldCoords = GameManager.getGameManager().getCamera().translatePixelToWorldCoordinates(x, y);
		EventQueue.getEventQueue().queue(new FireProjectileEvent(worldCoords, Projectile.TYPE_RAW, GameManager.getGameManager().getLevel().getHero()));
	}
	
}

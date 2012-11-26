package com.detourgames.raw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.detourgames.raw.Menu.MenuInput;
import com.detourgames.raw.Menu.MenuLoop;
import com.detourgames.raw.Menu.MenuManager;

public class TitleScreen implements Screen{
	
	MenuLoop mMenuLoop = new MenuLoop();
	MenuManager mMenuManager = MenuManager.getGameManager();
	
	GameRAW mGame;
	
	public TitleScreen(GameRAW game){
		mGame = game;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		mMenuLoop.tick();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		float ratio = (float)width/(float)height;
		mMenuManager.createCamera(7.5f*ratio, 7.5f, width, height);
		mMenuManager.loadMenu();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(new MenuInput());
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}

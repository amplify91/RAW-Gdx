package com.detourgames.raw.Menu;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class Menu implements ApplicationListener{

	MenuLoop mGameLoop;
	MenuManager mGameManager = MenuManager.getGameManager();
     
    
	@Override
	public void create() {
		mGameLoop=new MenuLoop();
		Gdx.input.setInputProcessor(new MenuInput());

	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render() {
		mGameLoop.tick();
		
	}

	@Override
	public void resize(int width, int height) {
		float ratio = (float)width/(float)height;
		mGameManager.createCamera(7.5f*ratio, 7.5f, width, height);
		mGameManager.loadMenu();
		
	}

	@Override
	public void resume() {
		
	}

}

package com.detourgames.raw.engineTest;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.detourgames.raw.SpriteFactory;
import com.detourgames.raw.SpriteSheet;

public class Menu implements ApplicationListener{

	MenuLoop mGameLoop;
	MenuManager mGameManager = MenuManager.getGameManager();
     
    
	@Override
	public void create() {
		mGameLoop=new MenuLoop();
		Gdx.input.setInputProcessor(new MenuInput());//TODO make this suck less

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		mGameLoop.tick();
		
	}

	@Override
	public void resize(int width, int height) {
		float ratio = (float)width/(float)height;
		mGameManager.createCamera(7.5f*ratio, 7.5f, width, height);
		mGameManager.loadLevel(1);
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}

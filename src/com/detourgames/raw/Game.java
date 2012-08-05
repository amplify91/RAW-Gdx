package com.detourgames.raw;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Game implements ApplicationListener {

	GameLoop mGameLoop;
	GameManager mGameManager = GameManager.getGameManager();
     
    SpriteFactory mSpriteFactory;
    SpriteSheet mSpriteSheet;
    
	@Override
	public void create() {
		mGameLoop = new GameLoop();
		Gdx.input.setInputProcessor(new Input());//TODO make this suck less
	}

	@Override
	public void dispose() {
	}
	
	@Override
	public void render() {
		
		mGameLoop.tick();
		// TODO either allow this to tick my game loop or
		// create my game loop as a thread and start it in resume().
		// The latter would require passing the OpenGL context to another
		// thread. That topic is covered by LibGdx here:
		// http://code.google.com/p/libgdx/wiki/ApplicationThreading
	}

	@Override
	public void resize(int width, int height) {
		
		float ratio = (float)width/(float)height;
		mGameManager.createCamera(7.5f*ratio, 7.5f, width, height);
		mGameManager.loadLevel(1);
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

package com.detourgames.raw;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen{
	
	GameLoop mGameLoop = GameLoop.getGameLoop();
	GameManager mGameManager = GameManager.getGameManager();
	
	GameRAW mGame;
	
	public GameScreen(GameRAW game){
		mGame = game;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		mGameLoop.tick();
	}

	@Override
	public void resize(int width, int height) {
		
		float ratio = (float)width/(float)height;
		mGameManager.createCamera(7.5f*ratio, 7.5f, width, height);
		mGameManager.loadLevel(1);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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

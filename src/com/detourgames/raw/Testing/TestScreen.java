package com.detourgames.raw.Testing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.detourgames.raw.GameLoop;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.RAWInput;

public class TestScreen implements Screen{

	GameLoop mGameLoop = GameLoop.getGameLoop();
	GameManager mGameManager = GameManager.getGameManager();
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		mGameLoop.tick();
	}

	@Override
	public void resize(int width, int height) {
		
		float ratio = (float)width/(float)height;
		mGameManager.createCamera(7.5f*ratio, 7.5f, width, height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new RAWInput());
		mGameManager.loadLevel(2);
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

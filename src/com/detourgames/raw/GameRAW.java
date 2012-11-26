package com.detourgames.raw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class GameRAW extends Game{

	
	public static TitleScreen mTitleScreen;
	public static GameScreen mGameScreen;
	
	long mLastTime = 0;
	long mCurrentTime;
	float mDeltaTime;
     
	
	private static GameRAW mGameRAW = new GameRAW(); //singleton
	
	public static GameRAW getGameRAW(){
		return mGameRAW;
	}
	
	@Override
	public void create() {
		Gdx.input.setInputProcessor(new RAWInput());//TODO make this suck less
		
		mTitleScreen = new TitleScreen(this);
		mGameScreen = new GameScreen(this);
		setScreen(mTitleScreen);
	}

	@Override
	public void dispose() {
		
	}
	
	@Override
	public void render() {
		
		mCurrentTime = TimeUtils.nanoTime();
		if(mLastTime==0){
			mLastTime = mCurrentTime-1;
		}
		mDeltaTime = ((float)(mCurrentTime - mLastTime))/1000000000f;
		
		getScreen().render(mDeltaTime);
		
		mLastTime = mCurrentTime;
		//mGameLoop.tick();
		// TODO either allow this to tick my game loop or
		// create my game loop as a thread and start it in resume().
		// The latter would require passing the OpenGL context to another
		// thread. That topic is covered by LibGdx here:
		// http://code.google.com/p/libgdx/wiki/ApplicationThreading
	}

	@Override
	public void resize(int width, int height) {
		
		getScreen().resize(width, height);
		/*float ratio = (float)width/(float)height;
		mGameManager.createCamera(7.5f*ratio, 7.5f, width, height);
		mGameManager.loadLevel(1);*/
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
}
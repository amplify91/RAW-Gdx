package com.detourgames.raw;

import com.badlogic.gdx.ApplicationListener;

public class Game implements ApplicationListener {
	
	GameLoop mGameLoop;
	
	@Override
	public void create() {
		mGameLoop = new GameLoop();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		mGameLoop.tick();
		//TODO either allow this to tick my game loop or
		// create my game loop as a thread and start it in resume().
		// The latter would require passing the OpenGL context to another
		// thread. That topic is covered by LibGdx here: 
		// http://code.google.com/p/libgdx/wiki/ApplicationThreading
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

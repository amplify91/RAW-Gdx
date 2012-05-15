package com.detourgames.raw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.TimeUtils;

public class GameLoop {

	public static final int TICKS_PER_SECOND = 25; // UPS
	public static final long TICK_DURATION = 1000000000 / TICKS_PER_SECOND;
	public static final int MAX_FRAMESKIP = 5;
	public static final float STEP_DURATION = 1.0f / (float) TICKS_PER_SECOND;

	long next_game_tick;
	int loops;
	float interpolation;
	boolean game_is_running;

	GameManager mGameManager;

	public GameLoop() {

		mGameManager = GameManager.getGameManager();

		game_is_running = false;

	}

	public void tick() {
		// TODO make sure this works without being a thread and can handle being
		// paused.
		next_game_tick = TimeUtils.nanoTime();

		loops = 0;
		while (TimeUtils.nanoTime() > next_game_tick && loops < MAX_FRAMESKIP) {
			updateGame(STEP_DURATION);
			next_game_tick += TICK_DURATION;
			loops++;
		}
		interpolation = ((float) (TimeUtils.nanoTime() + TICK_DURATION - next_game_tick)) / ((float) TICK_DURATION); // TODO change floats to longs
		updateGame(STEP_DURATION);
		displayGame(interpolation);

	}

	private void displayGame(float interpolation){
		
		Gdx.gl.glClearColor(0, 0, 0, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		mGameManager.draw();

	}

	private void updateGame(float deltaTime) {
		// gameManager.update(deltaTime);
	}

	public void pause() {
		// TODO manage time discrepancies
	}

	public void resume() {
		// TODO manage time discrepancies
	}

}

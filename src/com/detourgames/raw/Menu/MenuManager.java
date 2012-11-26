package com.detourgames.raw.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.detourgames.raw.Camera;
import com.detourgames.raw.EventQueue;
import com.detourgames.raw.HUD;
import com.detourgames.raw.LevelLoader;

public class MenuManager {

	private static MenuManager gameManager = new MenuManager();

	private Camera mCamera;
	private MenuHUD mHUD;
	// Input input;
	// HUD mHUD;

	MenuLevel mLevel;

	MenuLoader levelLoader;
	SpriteBatch spriteBatch;
	Box2DDebugRenderer debug;
	
	boolean levelLoaded = false;

	private MenuManager() {
		// input = new Input(this);
		// camera = new Camera();
		// mHUD = new HUD(camera);
		
	}

	public static MenuManager getGameManager() { // TODO synchronized?
		return gameManager;
	}

	public void update(long nanoTime) {
		if (levelLoaded) {
			// (float)mLevel.getHero().getY());
			// mHUD.update();
			EventQueue.getEventQueue().processAndRemoveAllEvents();
			mLevel.update(nanoTime, 8, 3);
			// TODO update camera AFTER level?
		}
	}
	
	public void draw(long nanoTime) {
		
		if (levelLoaded) {
			spriteBatch.setProjectionMatrix(mCamera.getCamera().combined);
			spriteBatch.begin();
			// mHUD.draw(spriteBatch);
			mLevel.draw(spriteBatch, nanoTime);
			//mHUD.draw(spriteBatch, nanoTime);
			spriteBatch.end();
			debug.render(mLevel.getWorld(), mCamera.getCamera().combined);
			//mLevel.drawDebug(mCamera);
		}

	}

	public void loadMenu() {

		levelLoaded = false;

		mLevel = new MenuLevel();
		spriteBatch = new SpriteBatch(1600);

		//mHeroTexture = new TextureAtlas();// new Texture(context,
											// Animation.HERO_TEXTURE, 3, new
											// int[]{3,1,5}, new
											// int[]{8,8,4,0,7,16,16,16,16}, new
											// int[]{128,1024,64}, new
											// int[]{128,320,64});
		// mHeroTexture.
		if (!levelLoaded) {
			levelLoader = new MenuLoader(mLevel);
			//levelLoader.createLevelFromFile(level);

			// spriteBatch = new SpriteBatch(1600, SpriteBatch.SPRITE_SHADER,
			// context, camera);

			levelLoaded = true;
		}
		
		debug = new Box2DDebugRenderer(true, true,true,true);

		// System.gc();
	}

	/*
	 * return mHUD; }
	 * 
	 * public Input getInput(){ return input; }
	 * 
	 * public Camera getCamera(){ return camera; }
	 */

	public MenuLevel getLevel() {
		return mLevel;
	}
	
	public void createCamera(float viewportWidth, float viewportHeight, int pixelWidth, int pixelHeight){
		mCamera = new Camera(viewportWidth, viewportHeight);
		mCamera.setScreenSizePixels(pixelWidth, pixelHeight);
		mHUD = new MenuHUD(mCamera);
	}
	
	public Camera getCamera(){
		return mCamera;
	}
	
	public MenuHUD getHUD(){
		return mHUD;
	}
	
}

package com.detourgames.raw;

import java.util.ArrayList;

import sun.rmi.runtime.Log;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.game.Hero;

public class Level {
	// This class will be used to manage multiple world objects within one
	// level.

	World mWorld;
	SpriteSheet mSpriteSheet;
	Hero mHero;
	
	public static final int LAYER_HUD = 0;
	public static final int LAYER_FOREGROUND = 1;
	public static final int LAYER_OVER_GFX = 2;
	public static final int LAYER_ACTORS_OBJECTS = 3;
	public static final int LAYER_UNDER_GFX = 4;
	public static final int LAYER_TERRAIN = 5;
	public static final int LAYER_BACKGROUND1 = 6;
	public static final int LAYER_BACKGROUND2 = 7;
	public static final int LAYER_BACKGROUND3 = 8;
	
	LevelLayer mHUD = new LevelLayer(LAYER_HUD);
	LevelLayer mForeGround = new LevelLayer(LAYER_FOREGROUND);
	LevelLayer mOverGFX = new LevelLayer(LAYER_OVER_GFX);
	LevelLayer mActorsObjects = new LevelLayer(LAYER_ACTORS_OBJECTS);
	LevelLayer mUnderGFX = new LevelLayer(LAYER_UNDER_GFX);
	LevelLayer mTerrain = new LevelLayer(LAYER_TERRAIN);
	LevelLayer mBackground1 = new LevelLayer(LAYER_BACKGROUND1);
	LevelLayer mBackground2 = new LevelLayer(LAYER_BACKGROUND2);
	LevelLayer mBackground3 = new LevelLayer(LAYER_BACKGROUND3);
	ArrayList<LevelLayer> mLayers = new ArrayList<LevelLayer>();
	
	//ArrayList<ArrayList<Sprite>> mDrawableSprites;
	ArrayList<Sprite> mUpdateableSprites = new ArrayList<Sprite>();
	
	long mCurrentTime = 0;
	long mLastTime = 0;
	float mDeltaTime = 0;
	
	public static final int VELOCITY_ITERATIONS = 8;
	public static final int POSITION_ITERATIONS = 3;

	// B2DDebugDraw debug;

	public Level() {

		Vector2 gravity = new Vector2(0.0f, -10.0f);
		mWorld = new World(gravity, true);
		mWorld.setContinuousPhysics(true);
		
		mLayers.add(mHUD);
		mLayers.add(mForeGround);
		mLayers.add(mOverGFX);
		mLayers.add(mActorsObjects);
		mLayers.add(mUnderGFX);
		mLayers.add(mTerrain);
		mLayers.add(mBackground1);
		mLayers.add(mBackground2);
		mLayers.add(mBackground3);
		checkLayers();
		
		setContactListeners();

		// debug = new B2DDebugDraw(null, context);//TODO if debug drawing
		// doesn't work, this null is probably why.

		// mWorld.setDebugDraw(debug);
		// debug.setFlags(B2DDebugDraw.e_shapeBit);
		// debug.setFlags(B2DDebugDraw.e_aabbBit);
	}

	/*
	 * public void drawDebug(Camera camera){ debug.beginSpriteBatch();
	 * mWorld.drawDebugData(); debug.endSpriteBatch(); //Log.i("debugDraw",
	 * "success!!!!"); }
	 */

	public void update(long nanoTime) {
		
		mCurrentTime = nanoTime;
		mDeltaTime = (mCurrentTime-mLastTime) / (float)1000000000;
		
		mWorld.step(mDeltaTime, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		
		for (int i = 0; i < mUpdateableSprites.size(); i++) {
			mUpdateableSprites.get(i).update(mCurrentTime, mDeltaTime);
		}
		
		mLastTime = mCurrentTime;
		
	}

	public void draw(SpriteBatch sb, long nanoTime) {
		//draw each layer in order back to front
		for (int i = mLayers.size()-1; i > LAYER_HUD; i--) {
			mLayers.get(i).drawLayer(sb, nanoTime);
		}
		if(Gdx.app.getType()==ApplicationType.Android){
			mHUD.drawLayer(sb, nanoTime);
		}
		
	}

	public void addDrawableSprite(Sprite sprite, int layerName) {
		/*if(layerName==LAYER_HUD){
			mHUD.add(sprite);
		}else if(layer==LAYER_FOREGROUND){
			mForeGround.add(sprite);
		}else if(layer==LAYER_OVER_GFX){
			mOverGFX.add(sprite);
		}else if(layer==LAYER_ACTORS_OBJECTS){
			mActorsObjects.add(sprite);
		}else if(layer==LAYER_UNDER_GFX){
			mUnderGFX.add(sprite);
		}else if(layer==LAYER_TERRAIN){
			mTerrain.add(sprite);
		}else if(layer==LAYER_BACKGROUND1){
			mBackground1.add(sprite);
		}else if(layer==LAYER_BACKGROUND2){
			mBackground2.add(sprite);
		}else if(layer==LAYER_BACKGROUND3){
			mBackground3.add(sprite);
		}*/
		mLayers.get(layerName).addSprite(sprite);
		//mDrawableSprites.add(sprite);
	}

	public void addUpdateableSprite(Sprite sprite) {
		mUpdateableSprites.add(sprite);
	}
	
	public void removeSprite(Sprite sprite){
		mUpdateableSprites.remove(sprite);
		for (int i = 0; i < mLayers.size(); i++) {
			mLayers.get(i).removeSprite(sprite);
		}
		//TODO make sure this works^
	}

	void step(float deltaTime) {
		mWorld.step(deltaTime, 8, 3);
	}
	
	private boolean checkLayers(){
		for(int i = 0; i < mLayers.size(); i++){
			if(mLayers.get(i).getName()!=i){
				System.out.println("Error with the LevelLayers. Check Level class.");
				return false;
			}
		}
		
		return true;
		
	}
	
	public World getWorld() {
		return mWorld;
	}
	
	//TODO change this to assign a different Hero to each client (for multiplayer).
	public void assignHero(Hero hero){
		mHero = hero;
	}
	
	public Hero getHero(){
		return mHero;
	}
	
	public void setSpriteSheet(SpriteSheet spriteSheet){
		mSpriteSheet = spriteSheet;
	}
	
	public SpriteSheet getSpriteSheet(){
		return mSpriteSheet;
	}
	
	private void setContactListeners(){
		ContactListenerRAW cl = new ContactListenerRAW();
		mWorld.setContactListener(cl);
	}
	
}

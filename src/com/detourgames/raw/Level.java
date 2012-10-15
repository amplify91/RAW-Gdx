package com.detourgames.raw;

import java.util.ArrayList;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Gdx.*;
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
	ArrayList<Sprite> mHUD = new ArrayList<Sprite>();
	ArrayList<Sprite> mForeGround = new ArrayList<Sprite>();
	ArrayList<Sprite> mOverGFX = new ArrayList<Sprite>();
	ArrayList<Sprite> mActorsObjects = new ArrayList<Sprite>();
	ArrayList<Sprite> mUnderGFX = new ArrayList<Sprite>();
	ArrayList<Sprite> mTerrain = new ArrayList<Sprite>();
	ArrayList<Sprite> mBackground1 = new ArrayList<Sprite>();
	ArrayList<Sprite> mBackground2 = new ArrayList<Sprite>();
	ArrayList<Sprite> mBackground3 = new ArrayList<Sprite>();
	//ArrayList<ArrayList<Sprite>> mDrawableSprites;
	ArrayList<Sprite> mUpdateableSprites;
	
	public static final int LAYER_HUD = 1;
	public static final int LAYER_FOREGROUND = 2;
	public static final int LAYER_OVER_GFX = 3;
	public static final int LAYER_ACTORS_OBJECTS = 4;
	public static final int LAYER_UNDER_GFX = 5;
	public static final int LAYER_TERRAIN = 6;
	public static final int LAYER_BACKGROUND1 = 7;
	public static final int LAYER_BACKGROUND2 = 8;
	public static final int LAYER_BACKGROUND3 = 9;

	// B2DDebugDraw debug;

	public Level() {

		Vector2 gravity = new Vector2(0.0f, -10.0f);
		mWorld = new World(gravity, true);
		mWorld.setContinuousPhysics(true);
		//mDrawableSprites = new ArrayList<ArrayList<Sprite>>();
		mUpdateableSprites = new ArrayList<Sprite>();
		
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

	public void update(float deltaTime, int velocityIterations, int positionIterations) {
		
		mWorld.step(deltaTime, velocityIterations, positionIterations);
		
		for (int i = 0; i < mUpdateableSprites.size(); i++) {
			mUpdateableSprites.get(i).update(deltaTime);
		}
		
	}

	public void draw(SpriteBatch sb, long nanoTime) {
		//draw each layer in order back to front
		for (int i = 0; i < mBackground3.size(); i++) {
			mBackground3.get(i).draw(sb, nanoTime);
		}
		for (int i = 0; i < mBackground2.size(); i++) {
			mBackground2.get(i).draw(sb, nanoTime);
		}
		for (int i = 0; i < mBackground1.size(); i++) {
			mBackground1.get(i).draw(sb, nanoTime);
		}
		for (int i = 0; i < mTerrain.size(); i++) {
			mTerrain.get(i).draw(sb, nanoTime);
		}
		for (int i = 0; i < mUnderGFX.size(); i++) {
			mUnderGFX.get(i).draw(sb, nanoTime);
		}
		for (int i = 0; i < mActorsObjects.size(); i++) {
			mActorsObjects.get(i).draw(sb, nanoTime);
		}
		for (int i = 0; i < mOverGFX.size(); i++) {
			mOverGFX.get(i).draw(sb, nanoTime);
		}
		for (int i = 0; i < mForeGround.size(); i++) {
			mForeGround.get(i).draw(sb, nanoTime);
		}
		if(Gdx.app.getType()==ApplicationType.Android){
			for (int i = 0; i < mHUD.size(); i++) {
				mHUD.get(i).draw(sb, nanoTime);
			}
		}
		
	}

	public void addDrawableSprite(Sprite sprite, int layer) {
		if(layer==LAYER_HUD){
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
		}
		//mDrawableSprites.add(sprite);
	}

	public void addUpdateableSprite(Sprite sprite) {
		mUpdateableSprites.add(sprite);
	}
	
	public void removeSprite(Sprite sprite){
		mUpdateableSprites.remove(sprite);
		mHUD.remove(sprite);
		mForeGround.remove(sprite);
		mOverGFX.remove(sprite);
		mActorsObjects.remove(sprite);
		mUnderGFX.remove(sprite);
		mTerrain.remove(sprite);
		mBackground1.remove(sprite);
		mBackground2.remove(sprite);
		mBackground3.remove(sprite);
		//TODO make sure this works^
	}

	void step(float deltaTime) {
		mWorld.step(deltaTime, 8, 3);
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

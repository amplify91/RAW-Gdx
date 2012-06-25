package com.detourgames.raw;

import java.util.ArrayList;

import javax.management.loading.MLet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level {
	// This class will be used to manage multiple world objects within one
	// level.

	World mWorld;
	SpriteSheet mSpriteSheet;
	Hero mHero;
	ArrayList<Sprite> mDrawableSprites;
	ArrayList<Sprite> mUpdateableSprites;

	// B2DDebugDraw debug;

	public Level() {

		Vector2 gravity = new Vector2(0.0f, -10.0f);
		mWorld = new World(gravity, true);
		mWorld.setContinuousPhysics(true);
		mDrawableSprites = new ArrayList<Sprite>();
		mUpdateableSprites = new ArrayList<Sprite>();
		
		TestContactListener test = new TestContactListener();
		mWorld.setContactListener(test);

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
		for (int i = 0; i < mDrawableSprites.size(); i++) {
			mDrawableSprites.get(i).draw(sb, nanoTime);
		}
	}

	/*
	 * Hero mHero; public void assignHero(Hero hero){ //TODO change this to
	 * assign a different Hero to each client (for multiplayer). mHero = hero; }
	 * public Hero getHero(){ return mHero; }
	 */

	public void addDrawableSprite(Sprite sprite) {
		mDrawableSprites.add(sprite);
	}

	public void addUpdateableSprite(Sprite sprite) {
		mUpdateableSprites.add(sprite);
	}

	void step(float deltaTime) {
		mWorld.step(deltaTime, 8, 3);
	}

	World getWorld() {
		return mWorld;
	}
	
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

}

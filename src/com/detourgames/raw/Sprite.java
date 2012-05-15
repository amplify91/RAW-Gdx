package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sprite {

	PhysicsComponent mPhysics;
	AnimationComponent mAnimation;

	float mDrawWidth = 0.5f;
	float mDrawHeight = 0.5f;
	float mDrawOffsetX = 0;
	float mDrawOffsetY = 0;

	// private Vector2 mProjectileSpawnPoint = new Vector2();

	public static final int VERTEX_SIZE = 2 + 2;
	public static final int SPRITE_SIZE = 4 * VERTEX_SIZE;
	// TODO put these 2 fields into render components
	public static final float SCALE_FACTOR = 2f / 15f;
	public static final float SCALE_FACTOR_INV = 15f / 2f;

	public Sprite(PhysicsComponent p, AnimationComponent a) {

		// renderable = new RenderVisible(context);
		mPhysics = p;
		mAnimation = a;

	}

	public void destroy() {
		// TODO
	}

	public void draw(SpriteBatch sb) {
		sb.draw(mAnimation.getCurrentFrame(), mPhysics.getX()+mDrawOffsetX, mPhysics.getY()+mDrawOffsetY, mDrawWidth, mDrawHeight);
	}

	public void update(float deltaTime) {
		mAnimation.update(deltaTime);
		mPhysics.update();
	}

	public void getInput() {

	}

	public float getX() {
		return mPhysics.getX();
	}

	public float getY() {
		return mPhysics.getY();
	}

	/*
	 * public float getOriginX(){ return mPhysics.getX() + mDrawWidth/2f; }
	 * 
	 * public float getOriginY(){ return mPhysics.getY() + mDrawHeight/2f; }
	 * 
	 * public Vec2 getProjectileSpawnPoint(){//TODO move to an interface like
	 * IProjectileParent or something
	 * mProjectileSpawnPoint.set(mPhysics.getX()+3, mPhysics.getY());//TODO
	 * change arguments to mPhysics.getProjectileSpawn return
	 * mProjectileSpawnPoint; }
	 */

	public void pauseAnimation() {
		mAnimation.pause();
	}

	public void resumeAnimation() {
		mAnimation.resume();
	}

}
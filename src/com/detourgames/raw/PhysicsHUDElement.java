package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsHUDElement extends PhysicsComponent{
	
	Vector2 mOrigin;
	Vector2 mWidthHeight;
	Vector2 mMinXY = new Vector2();
	Vector2 mMaxXY = new Vector2();
	Camera mCamera;
	
	public PhysicsHUDElement(Camera camera){
		mCamera = camera;
	}
	
	@Override
	public void update() {
		mBody.setTransform(mCamera.translatePixelToWorldCoordinates((int)mMinXY.x, (int)mMinXY.y).x + (mWidthHeight.x*mCamera.getWidth()/2f) + (mOrigin.x*mCamera.getWidth()), 
				(mCamera.translatePixelToWorldCoordinates((int)mMinXY.x, (int)mMinXY.y).y - mCamera.getHeight()) + (mWidthHeight.y*mCamera.getHeight()/2f) + (mOrigin.y*mCamera.getHeight()), 0);
	}
	
	public void create(World world, float originX, float originY, float width, float height){
		mOrigin = new Vector2(originX, originY);
		mWidthHeight = new Vector2(width, height);
		resize();
		super.create(world, 0, 0, mCamera.getWidth()*mWidthHeight.x, mCamera.getHeight()*mWidthHeight.y, false, FixtureType.NO_COLLISIONS);
	}
	
	/*public Vector2 getMinXY(){
		return mMinXY;
	}
	
	public void setMinXY(Vector2 minXY){
		mMinXY = minXY;
	}
	
	public Vector2 getMaxXY(){
		return mMaxXY;
	}
	
	public void setMaxXY(Vector2 maxXY){
		mMaxXY = maxXY;
	}*/
	
	public boolean isTouchInside(float x, float y){
		/*if(mBody.getFixtureList().get(0).testPoint(x, y)){
			return true;
		}else{
			System.out.println("x: "+x+" y: "+y+" testpoint: "+mBody.getFixtureList().get(0).testPoint(x, y));
			return false;
		}*/
		return mBody.getFixtureList().get(0).testPoint(x, y);
	}
	
	public void resize(){
		mMinXY.x = mOrigin.x * mCamera.getWidth();
		mMaxXY.x = mMinXY.x + (mWidthHeight.x * mCamera.getWidth());
		mMinXY.y = mOrigin.y * mCamera.getHeight();
		mMaxXY.y = mMinXY.y + (mWidthHeight.y * mCamera.getHeight());
	}
	
}
package com.detourgames.raw;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {
	
	private OrthographicCamera mCamera;
	
	public Camera(float viewportWidth, float viewportHeight){
		mCamera = new OrthographicCamera(viewportWidth, viewportHeight);
		mCamera.position.set(viewportWidth/2f, viewportHeight/2f, 0);
		mCamera.update();
	}
	
	public void update(IFocusable focus) {
		mCamera.position.set(focus.getX()-focus.getCameraOffsetX(), focus.getY()-focus.getCameraOffsetY(), 0);
		mCamera.update();
	}
	
	public OrthographicCamera getCamera(){
		return mCamera;
	}
	
}

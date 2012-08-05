package com.detourgames.raw;

public class HUDButton extends HUDElement{
	
	public HUDButton(Camera camera) {
		super(new PhysicsHUDElement(camera), new AnimationButton() , new StateNone(), new ControllerComponent());
		// TODO Auto-generated constructor stub
	}
	
	public void resize(){
		((PhysicsHUDElement)mPhysics).resize();
	}
	
	public boolean isTouchInside(float x, float y){
		return ((PhysicsHUDElement)mPhysics).isTouchInside(x, y);
	}
	
}

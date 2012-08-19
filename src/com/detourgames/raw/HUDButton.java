package com.detourgames.raw;

public class HUDButton extends HUDElement{
	
	public HUDButton(Camera camera, SpriteSheet spriteSheet) {
		super(new PhysicsHUDElement(camera), new AnimationButton(spriteSheet) , new StateButton(), new ControllerNone());
		// TODO Auto-generated constructor stub
	}
	
	public void resize(){
		((PhysicsHUDElement)mPhysics).resize();
	}
	
	public boolean isTouchInside(float x, float y){
		return ((PhysicsHUDElement)mPhysics).isTouchInside(x, y);
	}
	
}

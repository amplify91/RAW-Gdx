package com.detourgames.raw;

public abstract class HUDElement extends Sprite{

	public HUDElement(PhysicsComponent pc, AnimationComponent ac, StateComponent sc, ControllerComponent cc) {
		super(pc, ac, sc, cc);
	}
	
	public abstract boolean isTouchInside(float x, float y);
	
	public abstract void resize();

}

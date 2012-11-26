package com.detourgames.raw.Menu;

import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.StateComponent;

public abstract class MenuHUDElement extends Sprite{

	public MenuHUDElement(PhysicsComponent pc, AnimationComponent ac, StateComponent sc, ControllerComponent cc) {
		super(pc, ac, sc, cc, null);
	}
	
	public abstract boolean isTouchInside(float x, float y);
	
	public abstract void resize();

	public abstract void touchDown();
}

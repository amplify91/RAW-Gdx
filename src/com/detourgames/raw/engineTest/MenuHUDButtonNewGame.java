package com.detourgames.raw.engineTest;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.Camera;
import com.detourgames.raw.HUDButton;
import com.detourgames.raw.PhysicsHUDElement;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateButton;

public class MenuHUDButtonNewGame extends MenuHUDButton{
	
	private static final float ORIGIN_X = 0.1f;
	private static final float ORIGIN_Y = 0;
	private static final float WIDTH = 0.20f;
	private static final float HEIGHT = 1f/10f;
	
	public MenuHUDButtonNewGame(Camera camera, World world, SpriteSheet spriteSheet) {
		super(camera, spriteSheet);
		create(world);
		mState.setState(StateButton.STATE_DASH_UP);
	}
	
	public void create(World world){
		((PhysicsHUDElement)mPhysics).create(world, ORIGIN_X, ORIGIN_Y, WIDTH, HEIGHT);
		mAnimation.setSize(WIDTH*12.5f, HEIGHT*7.5f);//TODO
	}

	@Override
	public void Click() {
		
		// TODO Auto-generated method stub
		System.out.println("New Game!");
	}
	
}

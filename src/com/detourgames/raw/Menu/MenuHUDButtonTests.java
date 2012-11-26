package com.detourgames.raw.Menu;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.Camera;
import com.detourgames.raw.GameRAW;
import com.detourgames.raw.GameScreen;
import com.detourgames.raw.HUDButton;
import com.detourgames.raw.PhysicsHUDElement;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateButton;

public class MenuHUDButtonTests extends MenuHUDButton{
	
	private static final float WIDTH = 0.15f;
	private static final float HEIGHT = 0.25f;
	private static final float ORIGIN_X = 0.5f - WIDTH/2f;
	private static final float ORIGIN_Y = 5.5f - HEIGHT/2f;
	
	public MenuHUDButtonTests(Camera camera, World world, SpriteSheet spriteSheet) {
		super(camera, spriteSheet);
		create(world);
		mState.setState(StateButton.STATE_DASH_UP);
	}
	
	public void create(World world){
		((PhysicsHUDElement)mPhysics).create(world, ORIGIN_X, ORIGIN_Y, WIDTH, HEIGHT);
		mAnimation.setSize(WIDTH*12.5f, HEIGHT*7.5f);//TODO
	}

	@Override
	public void touchDown() {
		GameRAW.getGameRAW().setScreen(GameRAW.mGameScreen);
	}
	
}

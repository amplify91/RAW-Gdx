package com.detourgames.raw.game;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.Camera;
import com.detourgames.raw.HUDButton;
import com.detourgames.raw.PhysicsHUDElement;
import com.detourgames.raw.SpriteSheet;

public class HUDButtonDash extends HUDButton{
	
	private static final float ORIGIN_X = 0.1f;
	private static final float ORIGIN_Y = 0;
	private static final float WIDTH = 0.06f;
	private static final float HEIGHT = 1f/10f;
	
	public HUDButtonDash(Camera camera, World world, SpriteSheet spriteSheet) {
		super(camera);
		create(world);
	}
	
	public void create(World world){
		((PhysicsHUDElement)mPhysics).create(world, ORIGIN_X, ORIGIN_Y, WIDTH, HEIGHT);
	}
	
}

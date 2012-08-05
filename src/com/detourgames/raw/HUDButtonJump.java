package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.World;

public class HUDButtonJump extends HUDButton{
	
	private static final float ORIGIN_X = 0;
	private static final float ORIGIN_Y = 0;
	private static final float WIDTH = 0.1f;
	private static final float HEIGHT = 1f/6f;
	
	public HUDButtonJump(Camera camera, World world, SpriteSheet spriteSheet) {
		super(camera);
		create(world);
	}
	
	public void create(World world){
		((PhysicsHUDElement)mPhysics).create(world, ORIGIN_X, ORIGIN_Y, WIDTH, HEIGHT);
	}
	
}

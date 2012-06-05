package com.detourgames.raw;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Tile extends Sprite{
	
	private static final float HEIGHT = 0.5f;
	private static final float WIDTH = 0.5f;
	
	public static final int[] FRAME_TEST_TILES = { 28, 29, 30, 31, 32, 33, 34,
		35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
		52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68,
		69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85,
		86, 87, 88, 89, 90, 91 };
	
	public Tile(){
		super(new PhysicsStatic(), new AnimationComponent());
		
	}
	
	public void create(World world, float x, float y, Animation animation, Vector2[] vertices){
		
		mDrawWidth = WIDTH;
		mDrawHeight = HEIGHT;
		mDrawOffsetX = 0;
		mDrawOffsetY = 0;
		//mPhysics.create(world, x, y, 0.5f, 0.5f, false);
		mPhysics.create(world, x, y, vertices, false);
		
		
		mAnimation.setAnimation(animation);
		
	}
	
	public static int convertTileFrame(int frame){
		//TODO this is kind of an ugly way to do this.
		int f = 0;
		if(frame>0&&frame<65){
			f = FRAME_TEST_TILES[frame-1];
		}else{
			f = frame;
		}
		return f;
	}
	
	/*private Vector2[] getTileVertices(int frame){
		//TODO finish this. Plug in vertices for all different types of frames.
		Vector2[] verts;
		verts = new Vector2[]{new Vector2(0f,0f),new Vector2(0.5f,0f),new Vector2(0.5f,0.5f),new Vector2(0f,0.5f)};
		return verts;
	}*/
	
}

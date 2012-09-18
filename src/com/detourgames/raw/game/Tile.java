package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.AnimationStatic;
import com.detourgames.raw.ControllerNone;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.PhysicsStatic;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateStatic;

public class Tile extends Sprite{
	
	private static final float HEIGHT = 0.5f;
	private static final float WIDTH = 0.5f;
	
	public static final int[] FRAME_TEST_TILES = { 284, 285, 286, 287, 288, 289, 290,
		291, 292, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
		52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68,
		69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85,
		86, 87, 88, 89, 90, 91 };
	
	public static final int SHAPE_SQUARE = 0;
	public static final int SHAPE_30_UP_SMALL = 1;
	public static final int SHAPE_30_UP_BIG = 2;
	public static final int SHAPE_30_DOWN_SMALL = 3;
	public static final int SHAPE_30_DOWN_BIG = 4;
	public static final int SHAPE_45_UP = 5;
	public static final int SHAPE_45_DOWN = 6;
	public static final int SHAPE_60_UP_BIG = 7;
	public static final int SHAPE_60_UP_SMALL = 8;
	public static final int SHAPE_60_DOWN_BIG = 9;
	public static final int SHAPE_60_DOWN_SMALL = 10;
	public static final int SHAPE_HALF_TOP = 11;
	public static final int SHAPE_HALF_BOTTOM = 12;
	public static final int SHAPE_HALF_LEFT = 13;
	public static final int SHAPE_HALF_RIGHT = 14;
	public static final Vector2[] VERTS_SQUARE = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_30_UP_SMALL = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0f)};
	public static final Vector2[] VERTS_30_UP_BIG = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0f)};
	public static final Vector2[] VERTS_30_DOWN_SMALL = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(-0.25f,0f)};
	public static final Vector2[] VERTS_30_DOWN_BIG = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_30_UP_SMALL_INVERT = new Vector2[]{new Vector2(0.25f,0f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_30_UP_BIG_INVERT = new Vector2[]{new Vector2(-0.25f,0f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_30_DOWN_SMALL_INVERT = new Vector2[]{new Vector2(-0.25f,0f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_30_DOWN_BIG_INVERT = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,0f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_45_UP = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f)};
	public static final Vector2[] VERTS_45_DOWN = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_45_HALF_UP_SMALL = new Vector2[]{new Vector2(0f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0f)};
	public static final Vector2[] VERTS_45_HALF_UP_BIG = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(0f,0.25f),new Vector2(-0.25f,0f)};
	public static final Vector2[] VERTS_45_HALF_UP_SMALL_INVERT = new Vector2[]{new Vector2(0f,0.25f),new Vector2(0.25f,0f),new Vector2(0.25f,0.25f)};
	public static final Vector2[] VERTS_45_HALF_UP_BIG_INVERT = new Vector2[]{new Vector2(0f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f),new Vector2(-0.25f,0f)};
	//TODO 45_HALF_DOWN_ALL x4 & 60_ALL_INVERT
	public static final Vector2[] VERTS_60_UP_BIG = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(0f,0.25f)};
	public static final Vector2[] VERTS_60_UP_SMALL = new Vector2[]{new Vector2(0f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f)};
	public static final Vector2[] VERTS_60_DOWN_BIG = new Vector2[]{new Vector2(0f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_60_DOWN_SMALL = new Vector2[]{new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(0f,0.25f)};
	public static final Vector2[] VERTS_HALF_TOP = new Vector2[]{new Vector2(-0.25f,0f),new Vector2(0.25f,0f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_HALF_BOTTOM = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0f),new Vector2(-0.25f,0f)};
	public static final Vector2[] VERTS_HALF_LEFT = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0f,-0.25f),new Vector2(0f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_HALF_RIGHT = new Vector2[]{new Vector2(0f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(0f,0.25f)};
	
	public Tile(){
		super(new PhysicsStatic(), new AnimationStatic(), new StateStatic(), new ControllerNone());
		
	}
	
	public void create(World world, float x, float y, int frame, SpriteSheet spriteSheet){
		mAnimation.setSize(WIDTH, HEIGHT);
		//mPhysics.create(world, x, y, 0.5f, 0.5f, false);
		mPhysics.create(world, x, y, getTileVertices(getShapeFromFrame(frame)), false, FixtureType.TERRAIN);
		Animation animation = AnimationComponent.createAnimation(spriteSheet, new int[]{frame});
		mAnimation.setAnimation(animation);
		
	}
	
	public static int convertTileFrame(int frame){
		//TODO this is kind of an ugly way to do this.
		int f = 0;
		frame = frame %FRAME_TEST_TILES.length;
		if(frame>0&&frame<65){
			f = FRAME_TEST_TILES[frame-1];
		}else{
			f = frame;
		}
		return f;
	}
	
	private Vector2[] getTileVertices(int shape){
		
		Vector2[] verts;
		if(shape==SHAPE_SQUARE){
			verts = VERTS_SQUARE;
		}else if(shape==SHAPE_30_UP_SMALL){
			verts = VERTS_30_UP_SMALL;
		}else if(shape==SHAPE_30_UP_BIG){
			verts = VERTS_30_UP_BIG;
		}else if(shape==SHAPE_30_DOWN_SMALL){
			verts = VERTS_30_DOWN_SMALL;
		}else if(shape==SHAPE_30_DOWN_BIG){
			verts = VERTS_30_DOWN_BIG;
		}else if(shape==SHAPE_45_UP){
			verts = VERTS_45_UP;
		}else if(shape==SHAPE_45_DOWN){
			verts = VERTS_45_DOWN;
		}else if(shape==SHAPE_60_UP_BIG){
			verts = VERTS_60_UP_BIG;
		}else if(shape==SHAPE_60_UP_SMALL){
			verts = VERTS_60_UP_SMALL;
		}else if(shape==SHAPE_60_DOWN_BIG){
			verts = VERTS_60_DOWN_BIG;
		}else if(shape==SHAPE_60_DOWN_SMALL){
			verts = VERTS_60_DOWN_SMALL;
		}else if(shape==SHAPE_HALF_TOP){
			verts = VERTS_HALF_TOP;
		}else if(shape==SHAPE_HALF_BOTTOM){
			verts = VERTS_HALF_BOTTOM;
		}else if(shape==SHAPE_HALF_LEFT){
			verts = VERTS_HALF_LEFT;
		}else if(shape==SHAPE_HALF_RIGHT){
			verts = VERTS_HALF_RIGHT;
		}else{
			verts = VERTS_SQUARE;
		}
		return verts;
	}
	
	private int getShapeFromFrame(int frame){
		int shape = SHAPE_SQUARE;
		System.out.println(frame);
		if(frame==327 || frame==343){
			shape = SHAPE_30_UP_SMALL;
		}else if(frame==328 || frame==344){
			shape = SHAPE_30_UP_BIG;
		}else if(frame==331 || frame==347){
			shape = SHAPE_30_DOWN_SMALL;
		}else if(frame==330 || frame==346){
			shape = SHAPE_30_DOWN_BIG;
		}else if(frame==328 || frame==344){
			shape = SHAPE_30_UP_BIG;
		}else if(frame==388 || frame==404){
			shape = SHAPE_45_UP;
		}else if(frame==389 || frame==405){
			shape = SHAPE_45_DOWN;
		}else if(frame==256 || frame==257 || frame==258 || frame==267 || frame==268 || frame==269 || frame==270 || frame==271 || frame==272 || frame==273 || frame==274
				 || frame==320 || frame==321 || frame==322 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256
				 || frame==384 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256
				 || frame==448 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256 || frame==256){
			shape = SHAPE_SQUARE;
		}else{
			shape = SHAPE_SQUARE;
		}
		//TODO plug in remaining values from final spritesheet
		
		return shape;
	}
	
}

package com.detourgames.raw.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.AnimationComponent;
import com.detourgames.raw.AnimationStatic;
import com.detourgames.raw.ControllerNone;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.GenericPool;
import com.detourgames.raw.PhysicsStatic;
import com.detourgames.raw.Recyclable;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.StateStatic;

public class Tile extends Sprite{
	
	private static final float HEIGHT = 0.5f;
	private static final float WIDTH = 0.5f;
	
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
	public static final Vector2[] VERTS_60_DOWN_BIG = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_60_DOWN_SMALL = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0f,-0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_HALF_TOP = new Vector2[]{new Vector2(-0.25f,0f),new Vector2(0.25f,0f),new Vector2(0.25f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_HALF_BOTTOM = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0f),new Vector2(-0.25f,0f)};
	public static final Vector2[] VERTS_HALF_LEFT = new Vector2[]{new Vector2(-0.25f,-0.25f),new Vector2(0f,-0.25f),new Vector2(0f,0.25f),new Vector2(-0.25f,0.25f)};
	public static final Vector2[] VERTS_HALF_RIGHT = new Vector2[]{new Vector2(0f,-0.25f),new Vector2(0.25f,-0.25f),new Vector2(0.25f,0.25f),new Vector2(0f,0.25f)};
	
	public Tile(GenericPool<? extends Recyclable> genericPool){
		super(new PhysicsStatic(), new AnimationStatic(), new StateStatic(), new ControllerNone(), genericPool);
		
	}
	
	public void create(World world, float x, float y, int frame, SpriteSheet spriteSheet){
		mAnimation.setSize(WIDTH, HEIGHT);
		//mPhysics.create(world, x, y, 0.5f, 0.5f, false);
		//System.out.println("Tile created at: "+x+", "+y+".");
		mPhysics.create(world, x, y, getTileVertices(getShapeFromFrame(frame)), false, FixtureType.TERRAIN);
		Animation animation = AnimationComponent.createAnimation(spriteSheet, new int[]{frame});
		mAnimation.setAnimation(animation);
		
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
		//System.out.println(frame);
		
		switch(frame){
			case 327: case 343:
				shape = SHAPE_30_UP_SMALL;
				break;
			case 328: case 344:
				shape = SHAPE_30_UP_BIG;
				break;
			case 329: case 345:
				shape = SHAPE_30_DOWN_BIG;
				break;
			case 330: case 346:
				shape = SHAPE_30_DOWN_SMALL;
				break;
			case 388: case 404:
				shape = SHAPE_45_UP;
				break;
			case 389: case 405:
				shape = SHAPE_45_DOWN;
				break;
			case 391: case 407:
				shape = SHAPE_60_UP_SMALL;
				break;
			case 455: case 471:
				shape = SHAPE_60_UP_BIG;
				break;
			case 456: case 472:
				shape = SHAPE_60_DOWN_BIG;
				break;
			case 392: case 408:
				shape = SHAPE_60_DOWN_SMALL;
				break;
				//TODO plug in remaining values from final spritesheet
			default: shape = SHAPE_SQUARE;
				break;
		}
		
		return shape;
	}
	
}

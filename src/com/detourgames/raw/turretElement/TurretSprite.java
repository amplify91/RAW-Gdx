package com.detourgames.raw.turretElement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteSheet;

public class TurretSprite extends Sprite{
	
	Vector2 running;
	private static final float MAX_SPEED = 10;
	private static final float HEIGHT = 1;
	private static final float WIDTH = 1;
	
	public TurretSprite(SpriteSheet spriteSheet){
		super(new TurretComponent(), new AnimationTurret(spriteSheet,WIDTH,HEIGHT), new StateTurret());
		running = new Vector2(25, 0);
		
	}
	
	public void create(World world, float x, float y){
		mPhysics.create(world, x, y, WIDTH, HEIGHT, true, FixtureType.TURRET_BODY);
		
		//create ground sensor
		float sensorHeight = 0.1f;
		PolygonShape sensorBox = new PolygonShape();
		sensorBox.setAsBox((WIDTH-0.1f) / 2f, sensorHeight / 2f, new Vector2(0,(-HEIGHT/2f)-(sensorHeight/2f)), 0);
		FixtureDef sensorFixtureDef = new FixtureDef();
		sensorFixtureDef.shape = sensorBox;
		sensorFixtureDef.density = 1.0f;
		sensorFixtureDef.friction = 0.0f;
		sensorFixtureDef.isSensor = true;
	}
	
}

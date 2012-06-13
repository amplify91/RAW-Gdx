package com.detourgames.raw;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PhysicsProjectile extends PhysicsComponent{

		float mVelocity=10;
		Vector2 mDestinationPoint;
		
		public PhysicsProjectile(){
		}
		
		
		@Override
		public void update() {
		}
		
		public void setProjectileProperties(int type, Sprite parent, Vector2 destinationPoint){
			//TODO finish. also swap parent for just vec2 spawnpoint if no other info is needed from parent.
			mBody.setTransform(parent.getX(), parent.getY(), 0);
			//mType = type;
			//mSpawnPoint = spawnPoint;
			mDestinationPoint = destinationPoint;
			setType(type);
			this.mBody.applyLinearImpulse( this.getDistanceVectorToPoint(destinationPoint).nor().mul(mVelocity), this.getBody().getPosition());
			
		}
		
		private void setType(int type){
			
			if(type==Projectile.TYPE_RAW){
				mVelocity = Projectile.VELOCITY_RAW;
				mVertices = Projectile.VERTS_RAW;
				for(Fixture f : mBody.getFixtureList())
				{
					f.getFilterData().groupIndex = -TYPE_RAW;//TODO ideal place to do this, but gets reset by something, so this has no effect.
				}
			}
			if(type==Projectile.TYPE_ENEMY){
				mVelocity = Projectile.VELOCITY_RAW;
				mVertices = Projectile.VERTS_RAW;
				for(Fixture f : mBody.getFixtureList())
				{
					f.getFilterData().groupIndex = -TYPE_ENEMY;
				}
			}
			
		}
		@Override
		public void create(World world, float x, float y, Vector2 vertices[], boolean dynamic) {

			BodyDef bodyDef = new BodyDef();
			if (dynamic) {
				bodyDef.type = BodyType.DynamicBody;
			} else {
				bodyDef.type = BodyType.StaticBody;
			}
			bodyDef.position.set(x, y);
			mBody = world.createBody(bodyDef);

			PolygonShape dynamicBox = new PolygonShape();
			dynamicBox.set(vertices);
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = dynamicBox;
			fixtureDef.density = 1.0f;
			fixtureDef.friction = 0.0f;
			fixtureDef.filter.groupIndex=-TYPE_RAW;
			mBody.createFixture(fixtureDef);
			mBody.setFixedRotation(true);

		}

	
}

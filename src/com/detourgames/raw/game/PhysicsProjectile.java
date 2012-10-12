package com.detourgames.raw.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.FixtureType;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;

public class PhysicsProjectile extends PhysicsComponent{

		float mVelocity = 1f;//changed by this.SetType()
		Vector2 mDestinationPoint;
		
		public PhysicsProjectile(){
			super();
		}
		
		
		@Override
		public void update() {
			
		}
		
		public void setProjectileProperties(int type, Sprite parent, Vector2 destinationPoint){
			//TODO finish. also swap parent for just vec2 spawnpoint if no other info is needed from parent.
			mBody.setTransform(parent.getX(), parent.getY(), 0);
			//mType = type;
			mDestinationPoint = destinationPoint;
			setType(type);
			mBody.setTransform(mBody.getPosition(), (float)Math.toRadians(getDistanceVectorToPoint(destinationPoint).angle()));
			mBody.applyLinearImpulse( getDistanceVectorToPoint(destinationPoint).nor().mul(mVelocity).mul(mBody.getMass()), mBody.getWorldCenter());
			
		}
		
		private void setType(int type){
			
			if(type==Projectile.TYPE_RAW){
				mVelocity = Projectile.VELOCITY_RAW;
				//mVertices = Projectile.VERTS_RAW;
				for(Fixture f : mBody.getFixtureList())
				{
					Filter filter = f.getFilterData();
					filter.groupIndex = PhysicsComponent.GROUP_ALLY;
					f.setFilterData(filter);
					f.setUserData(FixtureType.HERO_PROJECTILE);
				}
			}
			if(type==Projectile.TYPE_ENEMY){
				mVelocity = Projectile.VELOCITY_RAW;
				//mVertices = Projectile.VERTS_RAW;
				for(Fixture f : mBody.getFixtureList())
				{
					Filter filter = f.getFilterData();
					filter.groupIndex = PhysicsComponent.GROUP_ENEMY;
					f.setFilterData(filter);
					//f.getFilterData().groupIndex = PhysicsComponent.GROUP_ENEMY;
					f.setUserData(FixtureType.TURRET_PROJECTILE);
				}
			}
			
		}
		
		@Override
		public void create(World world, float x, float y, Vector2 vertices[], boolean dynamic, int mainFixtureType) {

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
			fixtureDef.density = 0.1f;
			fixtureDef.friction = 0.0f;
			fixtureDef.filter.groupIndex = PhysicsComponent.GROUP_ALLY;
			ArrayList<Fixture> fixies = mBody.getFixtureList();
			mBody.createFixture(fixtureDef).setUserData(mainFixtureType);
			mBody.setGravityScale(0);
			mBody.setFixedRotation(true);
			//mBody.setLinearDamping(10.5f);
			mBody.setUserData(this);

		}

	
}

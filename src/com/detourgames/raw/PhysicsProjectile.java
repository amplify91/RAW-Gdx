package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;

public class PhysicsProjectile extends PhysicsComponent{

	//int mType;
		float mVelocity;
		Vector2 mVelocityVec = new Vector2(0, 1);
		//Vec2 mSpawnPoint;
		Vector2 mDestinationPoint;
		
		public PhysicsProjectile(){
			
		}
		
		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
		
		public void setProjectileProperties(int type, Sprite parent, Vector2 destinationPoint){
			//TODO finish. also swap parent for just vec2 spawnpoint if no other info is needed from parent.
			mBody.setTransform(parent.getX(), parent.getY(), 0);
			//mType = type;
			//mSpawnPoint = spawnPoint;
			mDestinationPoint = destinationPoint;
			setType(type);
			
		}
		
		private void setType(int type){
			
			if(type==Projectile.TYPE_RAW){
				mVelocity = Projectile.VELOCITY_RAW;
				mVertices = Projectile.VERTS_RAW;
			}
			
		}
	
}

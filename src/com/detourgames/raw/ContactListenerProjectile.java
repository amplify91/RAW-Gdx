package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListenerProjectile implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if(fixtureA.getUserData() != null){
			if((Integer)fixtureA.getUserData() == FixtureType.HERO_PROJECTILE){
				((StateProjectile)((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState).setState(StateComponent.STATE_HURTING);
			}
		}
		if(fixtureB.getUserData() != null){
			if((Integer)fixtureB.getUserData() == FixtureType.HERO_PROJECTILE){
				((StateProjectile)((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState).setState(StateComponent.STATE_HURTING);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}

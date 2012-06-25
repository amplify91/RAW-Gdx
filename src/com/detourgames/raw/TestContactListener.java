package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class TestContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if(fixtureA.getUserData() != null){
			if((Integer)fixtureA.getUserData() == 2){
				System.out.println("A");
				((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_RUNNING);
				
				
			}
		}
		if(fixtureB.getUserData() != null){
			if((Integer)fixtureB.getUserData() == 2){
				//gets to here
				System.out.println("B");
				((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_RUNNING);
				
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

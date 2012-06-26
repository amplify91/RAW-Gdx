package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class TestContactListener implements ContactListener{
	
	int numContacts = 0;
	
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if(fixtureA.getUserData() != null){
			if((Integer)fixtureA.getUserData() == 2){
				((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_RUNNING);
				numContacts++;
				((StateHero)((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState).isOnGround=true;
			}
		}
		if(fixtureB.getUserData() != null){
			if((Integer)fixtureB.getUserData() == 2){
				((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_RUNNING);
				numContacts++;
				((StateHero)((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState).isOnGround=true;
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if(fixtureA.getUserData() != null){
			if((Integer)fixtureA.getUserData() == 2){
				numContacts--;
				if(numContacts==0){
					//((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_FALLING);
					((StateHero)((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState).isOnGround=false;
				}
			}
		}
		if(fixtureB.getUserData() != null){
			if((Integer)fixtureB.getUserData() == 2){
				numContacts--;
				if(numContacts==0){
					//((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_FALLING);
					((StateHero)((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState).isOnGround=false;
				}
			}
		}
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

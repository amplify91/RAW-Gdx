package com.detourgames.raw;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListenerRAW implements ContactListener {

	@Override
	public void beginContact(Contact arg0) {
		Body bodyA=arg0.getFixtureA().getBody();
		Body bodyB=arg0.getFixtureB().getBody();
		Object userDataA=bodyA.getUserData();
		Object userDataB=bodyB.getUserData();
	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		String s = arg0.getClass().toString();
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}

}

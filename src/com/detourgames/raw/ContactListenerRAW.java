package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.detourgames.raw.ContactListenerInterfaces.BeginContactInterface;
import com.detourgames.raw.ContactListenerInterfaces.EndContactInterface;
import com.detourgames.raw.ContactListenerInterfaces.PostSolveInterface;
import com.detourgames.raw.ContactListenerInterfaces.PreSolveInterface;
import com.detourgames.raw.game.HeroProjectile;
import com.detourgames.raw.game.StateHero;
import com.detourgames.raw.game.TurretProjectile;

public class ContactListenerRAW implements ContactListener{
	
	private int numGroundContacts = 0;
	
	@Override
	public void beginContact(Contact contact) {
		beginGroundContact(contact);
		beginHeroProjectileContact(contact);
		beginEnemyProjectileContact(contact);

		 if (contact.getFixtureA() instanceof BeginContactInterface)
	          ((BeginContactInterface)contact.getFixtureA()).beginContact(contact);
		 if (contact.getFixtureB() instanceof BeginContactInterface)
	          ((BeginContactInterface)contact.getFixtureB()).beginContact(contact);
	}

	@Override
	public void endContact(Contact contact) {
		endGroundContact(contact);
		 if (contact.getFixtureA() instanceof EndContactInterface)
	          ((EndContactInterface)contact.getFixtureA()).endContact(contact);
		 if (contact.getFixtureB() instanceof EndContactInterface)
	          ((EndContactInterface)contact.getFixtureB()).endContact(contact);
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		 if (contact.getFixtureA() instanceof PreSolveInterface)
	          ((PreSolveInterface)contact.getFixtureA()).preSolve(contact);
		 if (contact.getFixtureB() instanceof PreSolveInterface)
	          ((PreSolveInterface)contact.getFixtureB()).preSolve(contact);

		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		 if (contact.getFixtureA() instanceof PostSolveInterface)
	          ((PostSolveInterface)contact.getFixtureA()).postSolve(contact);
		 if (contact.getFixtureB() instanceof PostSolveInterface)
	          ((PostSolveInterface)contact.getFixtureB()).postSolve(contact);
		
	}
	
	private void beginGroundContact(Contact contact){
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if(fixtureA.getUserData() != null){
			if((Integer)fixtureA.getUserData() == FixtureType.HERO_GROUND_SENSOR){
				if((Integer)fixtureB.getUserData() == FixtureType.TERRAIN){
					((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_RUNNING);
					numGroundContacts++;
					((StateHero)((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState).isOnGround=true;
				}
			}
		}
		if(fixtureB.getUserData() != null){
			if((Integer)fixtureB.getUserData() == FixtureType.HERO_GROUND_SENSOR){
				if((Integer)fixtureA.getUserData() == FixtureType.TERRAIN){
					((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_RUNNING);
					numGroundContacts++;
					((StateHero)((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState).isOnGround=true;
				}
			}
		}
	}
	
	private void endGroundContact(Contact contact){
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if(fixtureA.getUserData() != null){
			if((Integer)fixtureA.getUserData() == FixtureType.HERO_GROUND_SENSOR){
				if((Integer)fixtureB.getUserData() == FixtureType.TERRAIN){
					numGroundContacts--;
					if(numGroundContacts==0){
						//((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_FALLING);
						((StateHero)((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState).isOnGround=false;
					}
				}
			}
		}
		if(fixtureB.getUserData() != null){
			if((Integer)fixtureB.getUserData() == FixtureType.HERO_GROUND_SENSOR){
				if((Integer)fixtureA.getUserData() == FixtureType.TERRAIN){
					numGroundContacts--;
					if(numGroundContacts==0){
						//((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.setState(StateComponent.STATE_FALLING);
						((StateHero)((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState).isOnGround=false;
					}
				}
			}
		}
	}
	
	private void beginHeroProjectileContact(Contact contact){
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if(fixtureA.getUserData() != null){
			if((Integer)fixtureA.getUserData() == FixtureType.HERO_PROJECTILE){
				((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.kill();
				if(fixtureB.getUserData() != null){
					((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState.receiveDamage(HeroProjectile.DAMAGE);
				}
			}
		}
		if(fixtureB.getUserData() != null){
			if((Integer)fixtureB.getUserData() == FixtureType.HERO_PROJECTILE){
				((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState.kill();
				if(fixtureA.getUserData() != null){
					((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.receiveDamage(HeroProjectile.DAMAGE);
				}
			}
		}
	}
	
	private void beginEnemyProjectileContact(Contact contact){
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if(fixtureA.getUserData() != null){
			if((Integer)fixtureA.getUserData() == FixtureType.TURRET_PROJECTILE){
				((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.kill();
				if(fixtureB.getUserData() != null){
					((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState.receiveDamage(TurretProjectile.DAMAGE);
				}
			}
		}
		if(fixtureB.getUserData() != null){
			if((Integer)fixtureB.getUserData() == FixtureType.TURRET_PROJECTILE){
				((PhysicsComponent)fixtureB.getBody().getUserData()).getParentSprite().mState.kill();
				if(fixtureA.getUserData() != null){
					((PhysicsComponent)fixtureA.getBody().getUserData()).getParentSprite().mState.receiveDamage(TurretProjectile.DAMAGE);
				}
			}
		}
	}
	
}

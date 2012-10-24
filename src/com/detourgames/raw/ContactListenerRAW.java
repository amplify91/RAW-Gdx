package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
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
	}

	@Override
	public void endContact(Contact contact) {
		endGroundContact(contact);
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
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

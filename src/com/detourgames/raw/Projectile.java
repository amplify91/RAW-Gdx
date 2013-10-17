package com.detourgames.raw;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.game.AnimationTurretProjectile;
import com.detourgames.raw.game.StateHeroProjectile;

public class Projectile extends Sprite{

	public static final float WIDTH = 0.4f;
	public static final float HEIGHT = 0.4f;
	
	public static final float VELOCITY = 20.0f;
	
	public static final int DAMAGE = 20;
	public static final Vector2[] VERTS = new Vector2[]{new Vector2(-0.2f,-0.2f), new Vector2(0.2f,-0.2f), new Vector2(0.2f,0.2f), new Vector2(-0.2f,0.2f)};
	
	public Projectile(SpriteSheet spriteSheet, GenericPool<?> pool, ControllerComponent controller) {
		super(new PhysicsStatic(), new AnimationTurretProjectile(spriteSheet, WIDTH, HEIGHT), new StateHeroProjectile(),
				controller, pool);
	}
	public Projectile(SpriteSheet spriteSheet, GenericPool<?> pool)
	{
		super(new PhysicsStatic(), new AnimationTurretProjectile(spriteSheet, WIDTH, HEIGHT), new StateHeroProjectile(),
				new ControllerNone(), pool);
	}
	public Projectile(PhysicsComponent pc, AnimationComponent ac, StateComponent sc, ControllerComponent cc, GenericPool<?> pool) {
		super(pc,ac,sc,cc,pool);
	}
	public void create(World world, Sprite parent, Vector2 destinationPoint){
		mPhysics.createBody(world, parent.getPosition().x, parent.getPosition().y, true, true, true, 0, 0, 0);
		mPhysics.createFixture(VERTS, FixtureType.TURRET_PROJECTILE, 0.1f, 0, 0, false, PhysicsComponent.GROUP_ENEMY, PhysicsComponent.CATEGORY_ENEMY, PhysicsComponent.MASK_ALL_COLLISIONS);//add more masks with |
		mPhysics.getBody().setTransform(parent.getPosition(), (float)Math.toRadians(mPhysics.getDistanceVectorToPoint(destinationPoint).angle()));
		mPhysics.getBody().applyLinearImpulse( mPhysics.getDistanceVectorToPoint(destinationPoint).nor().mul(VELOCITY).mul(mPhysics.getBody().getMass()), mPhysics.getBody().getWorldCenter());
		mState.setInitialState();
	}
	public void setController(ControllerComponent cc)
	{
		this.mController=cc;
	}
}

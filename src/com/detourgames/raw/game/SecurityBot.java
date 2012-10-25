package com.detourgames.raw.game;

import com.badlogic.gdx.physics.box2d.World;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.GenericPool;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.StateComponent;

public class SecurityBot extends Sprite{

	public SecurityBot(PhysicsComponent pc, StateComponent sc, ControllerComponent cc, GenericPool<SecurityBot> pool) {
		super(pc, new AnimationSecurityBot(), sc, cc, pool);
		// TODO Auto-generated constructor stub
	}
	
	public void create(World world, float x, float y){
		
	}

}

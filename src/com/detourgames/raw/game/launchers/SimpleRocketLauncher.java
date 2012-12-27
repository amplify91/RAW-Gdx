package com.detourgames.raw.game.launchers;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteFactory;

public class SimpleRocketLauncher implements IProjectileLauncher  {

	static SimpleRocketLauncher launcher;
	@Override
	public void fireProjectile(Sprite mShootingSprite, Sprite mTarget, Vector2 mDestination) {
		SpriteFactory.getSpriteFactory().createTurretProjectile(mShootingSprite, mDestination);
		
	}
	public static SimpleRocketLauncher getSimpleRocketLauncher(){
		if(launcher==null)
			launcher=new SimpleRocketLauncher();
		return launcher;
	}
	private SimpleRocketLauncher(){}

}

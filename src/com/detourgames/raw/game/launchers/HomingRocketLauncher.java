package com.detourgames.raw.game.launchers;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Sprite;
import com.detourgames.raw.SpriteFactory;

public class HomingRocketLauncher implements IProjectileLauncher {

	private static HomingRocketLauncher launcher;
	@Override
	public void fireProjectile(Sprite mShootingSprite, Sprite mTarget,Vector2 mDestination) {
		SpriteFactory.getSpriteFactory().createHomingProjectile(mShootingSprite, mTarget);
		
	}
	
	public static HomingRocketLauncher getHomingRocketLauncher(){
		if(launcher==null)
			launcher=new HomingRocketLauncher();
		return launcher;
	}
	
	private HomingRocketLauncher(){}

}

package com.detourgames.raw.game.launchers;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Sprite;

public interface IProjectileLauncher {
	void fireProjectile(Sprite mShootingSprite, Sprite mTarget, Vector2 mDestination);
}

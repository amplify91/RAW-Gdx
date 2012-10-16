package com.detourgames.raw.game;

import com.detourgames.raw.Event;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.Sprite;

public class SpriteDeathEvent extends Event{
	
	Sprite mSprite;
	
	public SpriteDeathEvent(Sprite sprite){
		mSprite = sprite;
	}
	
	@Override
	public void executeEvent() {
		mSprite.recycle();
		GameManager.getGameManager().getLevel().removeSprite(mSprite);
		//TODO remove from world
		mSprite.getBody().getWorld().destroyBody(mSprite.getBody());
		//TODO spawn death graphic
	}

}

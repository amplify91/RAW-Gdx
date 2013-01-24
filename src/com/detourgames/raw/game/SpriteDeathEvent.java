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
		
		GameManager.getGameManager().getLevel().removeSprite(mSprite);
		//System.out.println(mSprite.getBody());
		//mSprite.getBody().getWorld().destroyBody(mSprite.getBody());
		mSprite.destroy();
		
		//TODO spawn death graphic
	}

}

package com.detourgames.raw.game;

import com.detourgames.raw.Event;
import com.detourgames.raw.GameManager;

public class SwingEvent extends Event{
	
	public SwingEvent(){
		
	}
	
	@Override
	public void executeEvent() {
		GameManager.getGameManager().getLevel().getHero().swing();
	}

}

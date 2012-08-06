package com.detourgames.raw.game;

import com.detourgames.raw.Event;
import com.detourgames.raw.GameManager;


public class DashEvent extends Event{

	@Override
	public void executeEvent() {
		GameManager.getGameManager().getLevel().getHero().dash();
	}

}

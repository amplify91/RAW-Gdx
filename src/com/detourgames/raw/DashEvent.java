package com.detourgames.raw;


public class DashEvent extends Event{

	@Override
	public void executeEvent() {
		GameManager.getGameManager().getLevel().getHero().dash();
	}

}

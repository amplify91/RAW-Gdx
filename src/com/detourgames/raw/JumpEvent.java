package com.detourgames.raw;

public class JumpEvent extends Event{
	
	
	
	@Override
	public void executeEvent() {
		GameManager.getGameManager().getLevel().getHero().jump();
	}

}

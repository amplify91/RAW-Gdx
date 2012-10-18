package com.detourgames.raw.engineTest;

import com.detourgames.raw.SpriteSheet;

public class MenuSpriteFactory {

	MenuLevel mLevel;
	SpriteSheet mSpriteSheet;

	public MenuSpriteFactory(MenuLevel level) {
		mLevel = level;
		mSpriteSheet = mLevel.getSpriteSheet();
		
	}
	
	public void createHUDElements(){
		MenuManager.getGameManager().getHUD().createElements(mLevel, mSpriteSheet);
	}
	
}

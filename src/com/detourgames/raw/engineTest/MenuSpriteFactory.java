package com.detourgames.raw.engineTest;

import com.detourgames.raw.GameManager;
import com.detourgames.raw.SpriteSheet;
import com.detourgames.raw.game.BackgroundTile;
import com.detourgames.raw.game.Hero;
import com.detourgames.raw.game.Projectile;
import com.detourgames.raw.game.Tile;
import com.detourgames.raw.game.Turret;

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

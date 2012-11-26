package com.detourgames.raw.game.chaserbot;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.Event;
import com.detourgames.raw.Sprite;

public class ChaserBotMoveEvent extends Event{
	
	Vector2 destination;
	Sprite movingSprite;
	public ChaserBotMoveEvent(Vector2 destination, Sprite movingSprite){
		this.destination=destination;
		this.movingSprite=movingSprite;
	}
	public ChaserBotMoveEvent(Sprite target, Sprite movingSprite){
	}
	
	@Override
	public void executeEvent() {
	}
}

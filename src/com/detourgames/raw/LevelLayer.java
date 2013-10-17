package com.detourgames.raw;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelLayer {
	
	ArrayList<Sprite> mSprites = new ArrayList<Sprite>();
	
	private int NAME;
	
	public LevelLayer(int name){
		this.NAME = name;
	}
	
	public void drawLayer(SpriteBatch sb, long nanoTime){
		for (int i = 0; i < mSprites.size(); i++) {
			mSprites.get(i).draw(sb, nanoTime);
		}
	}

	public int getName() {
		return NAME;
	}
	
	public Sprite getSprite(int i){
		return mSprites.get(i);
	}
	
	public void addSprite(Sprite sprite){
		mSprites.add(sprite);
	}
	
	public void removeSprite(Sprite sprite){
		mSprites.remove(sprite);
	}
	
	public ArrayList<Sprite> getSprites(){
		return mSprites;
	}
	
}

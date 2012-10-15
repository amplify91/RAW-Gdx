package com.detourgames.raw;

import com.badlogic.gdx.utils.Pool;

public class GenericPool<T> extends Pool<T> {
	
	@Override
	protected T newObject(){
		return null;
	}
	
	public void free(T sprite){
		free(sprite);
	}

	public void free(Sprite sprite) {
		free((T)sprite);
	}
	
}

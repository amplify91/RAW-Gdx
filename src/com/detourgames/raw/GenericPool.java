package com.detourgames.raw;

import com.badlogic.gdx.utils.Pool;

public class GenericPool<T> extends Pool<T> {
	
	@Override
	protected T newObject(){
		return null;
	}
	
	/*public void free(T sprite){
		super.free(sprite);
	}*/

	@SuppressWarnings("unchecked")
	public void free(Sprite sprite) {
		super.free((T)sprite);
	}
	
}

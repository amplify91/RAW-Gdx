package com.detourgames.raw;

import com.badlogic.gdx.utils.Pool;

public class GenericPool<T> extends Pool {

	@Override
	protected Object newObject() {
		return null;
	}
}

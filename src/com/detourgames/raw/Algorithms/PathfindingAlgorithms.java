package com.detourgames.raw.Algorithms;

import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.GameManager;

public class PathfindingAlgorithms {

	
	public static Vector2 PathfindStupid(Vector2 from, Vector2 to)
	{
		return to.sub(from).nor();
	}
}
